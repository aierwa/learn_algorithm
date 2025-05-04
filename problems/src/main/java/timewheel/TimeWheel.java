package timewheel;

import java.util.Date;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ExecutorService;

public class TimeWheel {
    static class TimeTask {
        // 需要推迟多少时间触发，单位s
        int delay;
        Runnable runnable;

        TimeTask(int delay, Runnable runnable) {
            this.delay = delay;
            this.runnable = runnable;
        }
    }

    // 时间轮槽数
    private final int slotNum;
    // 当前时间槽
    private int currentSlot;
    // tick的间隔时间，单位s
    // 相当于精度，比如1s说明每秒触发一次，10s说明delay为10到20s之间的都会一起触发
    private final int interval;
    // 槽为数组结构，读取性能高，每个槽用list来存放任务
    private Queue<TimeTask>[] slots;
    // 下一层时间轮，比如第一层可以是以1s为精度，第二层是60s为精度，这样两层时间轮共计120个槽可以存放3600s内的待触发任务，否则单层的话需要3600个槽
    private TimeWheel nextLayer;
    // 上一层时间轮，用于将任务重新放入上一层时间轮
    private TimeWheel preLayer;
    private ExecutorService executorService;

    public TimeWheel(int slotNum) {
        this(slotNum, 1);
    }

    public TimeWheel(int slotNum, int interval) {
        this.slotNum = slotNum;
        this.interval = interval;
        this.slots = new Queue[slotNum];
        for (int i = 0; i < slotNum; i++) {
            this.slots[i] = new ConcurrentLinkedQueue<>();
        }
    }

    public void addTask(int time, Runnable runnable) {
        if (time / interval > slotNum) {
            if (nextLayer != null) {
                // 下一层添加的时候需要延迟时间加上当前轮的时间
                nextLayer.addTask(time + interval * currentSlot, runnable);
            } else {
                System.out.println("no next layer.");
            }
        } else {
            int slot = (currentSlot + (time - interval) / interval) % slotNum;
            slots[slot].add(new TimeTask(time, runnable));
        }
    }

    public void addTask(TimeTask task) {
        int time = task.delay;
        if (time / interval > slotNum) {
            if (nextLayer != null) {
                task.delay += interval * slotNum;
                nextLayer.addTask(task);
            } else {
                System.out.println("no next layer.");
            }
        } else {
            int slot = (currentSlot + (time - interval) / interval) % slotNum;
            slots[slot].add(task);
        }
    }

    public void setNextLayer(TimeWheel nextLayer) {
        this.nextLayer = nextLayer;
        nextLayer.preLayer = this;
    }

    public void setExecutorService(ExecutorService executorService) {
        this.executorService = executorService;
    }

    public void tick() {
        Queue<TimeTask> tasks = slots[currentSlot];
        for (TimeTask task : tasks) {
            if (preLayer != null) {
                // 如果有上一层时间轮，那么tick本时间轮时，需要把任务重新放入上一层时间轮，最终会放到精度最高的那一层
                // 这里的delay需要转换为上一层的精度，即取模时间范围，比如分钟放入秒，取模60，小时放入分钟，取模3600
                task.delay = task.delay % (preLayer.interval * preLayer.slotNum);
                preLayer.addTask(task);
            } else if (executorService != null) {
                executorService.execute(task.runnable);
            } else {
                task.runnable.run();
            }
        }
        tasks.clear();
        currentSlot = (currentSlot + 1) % slotNum;

        //  每轮时间轮执行完毕，tick下一层时间轮
        if (currentSlot == 0 && nextLayer != null) {
            nextLayer.tick();
        }
    }

    public void start() {
        new Thread(() -> {
            while (true) {
                tick();
                try {
                    Thread.sleep(interval * 1000L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public static void main(String[] args) throws InterruptedException {
        System.out.println("start at:" + new Date());
        // 单层60s
//        TimeWheel timeWheel = new TimeWheel(60);
//        timeWheel.start();
//        timeWheel.addTask(5, () -> System.out.println("5s task run at:" + new Date()));
//        System.out.println("add 5s task at:" + new Date());
//
//        Thread.sleep(2000);
//
//        timeWheel.addTask(6, () -> System.out.println("6s task run at:" + new Date()));
//        System.out.println("add 5s task at:" + new Date());
//        timeWheel.addTask(10, () -> System.out.println("10s task run at:" + new Date()));
//        System.out.println("add 10s task at:" + new Date());


        // 双层60s，加60分钟
//        TimeWheel secondWheel = new TimeWheel(60, 1);
//        TimeWheel minuteWheel = new TimeWheel(60, 60); // 每60s tick一次，即分钟
//        secondWheel.setNextLayer(minuteWheel);
//        secondWheel.start();
//
//        secondWheel.addTask(5, () -> System.out.println("5s task run at:" + new Date()));
//        System.out.println("add 5s task at:" + new Date());
//        secondWheel.addTask(6, () -> System.out.println("6s task run at:" + new Date()));
//        System.out.println("add 6s task at:" + new Date());
//        secondWheel.addTask(10, () -> System.out.println("10s task run at:" + new Date()));
//        System.out.println("add 10s task at:" + new Date());
//        secondWheel.addTask(70, () -> System.out.println("70s task run at:" + new Date()));
//        System.out.println("add 70s task at:" + new Date());
//        secondWheel.addTask(125, () -> System.out.println("125s task run at:" + new Date()));
//        System.out.println("add 125s task at:" + new Date());
//        Thread.sleep(10000);
//        secondWheel.addTask(10, () -> System.out.println("10s task run at:" + new Date()));
//        System.out.println("add 10s task at:" + new Date());
//        secondWheel.addTask(70, () -> System.out.println("70s task run at:" + new Date()));
//        System.out.println("add 70s task at:" + new Date());

        // 三层，时分秒
        TimeWheel secondWheel = new TimeWheel(60, 1);
        TimeWheel minuteWheel = new TimeWheel(60, 60);
        TimeWheel hourWheel = new TimeWheel(24, 60 * 60);
        secondWheel.setNextLayer(minuteWheel);
        minuteWheel.setNextLayer(hourWheel);
        secondWheel.start();

        secondWheel.addTask(10, () -> System.out.println("10s task run at:" + new Date()));
        System.out.println("add 10s task at:" + new Date());
        secondWheel.addTask(70, () -> System.out.println("70s task run at:" + new Date()));
        System.out.println("add 70s task at:" + new Date());
        secondWheel.addTask(3610, () -> System.out.println("3610s task run at:" + new Date()));
        System.out.println("add 3610s task at:" + new Date());

        Thread.sleep(100000);
        secondWheel.addTask(10, () -> System.out.println("10s task run at:" + new Date()));
        System.out.println("add 10s task at:" + new Date());
        secondWheel.addTask(70, () -> System.out.println("70s task run at:" + new Date()));
        System.out.println("add 70s task at:" + new Date());
        secondWheel.addTask(3610, () -> System.out.println("3610s task run at:" + new Date()));
        System.out.println("add 3610s task at:" + new Date());

        Thread.sleep(20000000);
    }

}
