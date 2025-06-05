import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class Send {
    public static void startSendint(String friendNickName, String content) {
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

    public static void sendMsg(String content) throws InterruptedException {
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

    public static void sendOneMsg(String msg) {
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

    public static Robot getRobot() {
        try {
            return new Robot();
        } catch (AWTException e) {
            e.printStackTrace();
        }
        return null;
    }
}
