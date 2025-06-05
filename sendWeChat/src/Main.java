import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
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

        startSendint(friendNickName, content);

        timeSleep(intervalMillis);

        while(true){
            timeSleep(intervalMillis);
            try {
                sendMsg("当前时间为：" + getTime());
                sendMsg(content);
                sendMsg("已提醒你"+count+"次，别给脸不要脸！");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            count++;
        }
    }

    private static void startSendint(String friendNickName, String content) {
        Robot robot = getRobot();
        if (robot == null) return;

        // 打开微信 Ctrl+Alt+W
        robot.keyPress(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_ALT);
        robot.keyPress(KeyEvent.VK_W);
        robot.keyRelease(KeyEvent.VK_CONTROL);
        robot.keyRelease(KeyEvent.VK_ALT);
        robot.delay(1000);

        // Ctrl + F 搜索指定好友
        robot.keyPress(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_F);
        robot.keyRelease(KeyEvent.VK_CONTROL);
        Clipboard clip = Toolkit.getDefaultToolkit().getSystemClipboard();
        Transferable tText = new StringSelection(friendNickName);
        clip.setContents(tText, null);
        robot.keyPress(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_V);
        robot.keyRelease(KeyEvent.VK_CONTROL);
        robot.delay(1000);
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.delay(1000);

        // 发送消息
        try {
            sendMsg("奉天承运，皇帝诏曰,"+friendNickName+"听令！");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static void sendMsg(String content) throws InterruptedException {
        if (!content.equals("发送默认的信息")) {
            sendOneMsg(content);
        } else {
            ArrayList<String> msgList = new ArrayList<>();
            for (int i = 0; i < 10; i++) {
                msgList.add("能不能发！");
            }
            for (String item : msgList) {
                sendOneMsg(item);
            }
            Thread.sleep(2000);
            sendOneMsg("就问你能不能发！");
            sendOneMsg("[坏笑]");
            sendOneMsg("鸹貔");
        }
    }

    private static void sendOneMsg(String msg) {
        Clipboard clip = Toolkit.getDefaultToolkit().getSystemClipboard();
        Transferable tText = new StringSelection(msg);
        clip.setContents(tText, null);
        Robot robot = getRobot();
        if (robot != null) {
            robot.delay(500);
            robot.keyPress(KeyEvent.VK_CONTROL);
            robot.keyPress(KeyEvent.VK_V);
            robot.keyRelease(KeyEvent.VK_CONTROL);
            robot.delay(500);
            robot.keyPress(KeyEvent.VK_ENTER);
            robot.delay(500);
        }
    }

    private static Robot getRobot() {
        try {
            return new Robot();
        } catch (AWTException e) {
            e.printStackTrace();
        }
        return null;
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