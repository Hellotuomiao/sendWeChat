import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Main {
    // 执行次数
    private static int count = 1;

    public static void main(String[] args) {
        System.out.println("开始执行----------");
        Scanner scanner = new Scanner(System.in);

        System.out.println("请设置需发送的好友昵称：");
        String friendNickName = scanner.next();

        System.out.println("请设置需发送的内容：");
        String content = scanner.next();

        System.out.println("请输入每隔多少秒发送一次：");
        int intervalSeconds = scanner.nextInt();

        long intervalMillis = intervalSeconds * 1000L;  // 转换为毫秒
        System.out.println("您设定的发送间隔为每隔 " + intervalSeconds + " 秒！！！");

        Send send = new Send();
        send.startSendint(friendNickName, content);

        timeSleep(intervalMillis);

        while(true){
            timeSleep(intervalMillis);
            try {
                send.sendMsg("当前时间为：" + getTime());
                send.sendMsg(content);
                send.sendMsg("已提醒你"+count+"次，别给脸不要脸！");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            count++;
        }
    }

    public static String getTime() {
        LocalDateTime now = LocalDateTime.now(); // 获取当前时间
        // 格式化为字符串
        return now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }


    public static void timeSleep(long time_num){
        try {
            Thread.sleep(time_num);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}