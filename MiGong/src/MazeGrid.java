import java.awt.*;
@SuppressWarnings("serial")  
public class MazeGrid extends Canvas {  //Canvas控件代表一个矩形区域，应用程序可以画的东西，或者可以由用户创建的接收输入。
                                    //用法类似与Frame
    private boolean mark;// 标记是否是通路，TRUE为通路，FALSE为不通  
    private boolean isVisited;// 标记是否是访问过的,这是在生成迷宫的时候判断的。  
    private boolean isPersonCome;// 标记是否已经走过  
    private boolean isStart;// 判断是否为入口  
    private boolean isEnd;// 判断是否为出口  
    public MazeGrid() {  
        this.setBackground(new Color(120, 0, 0));  
        this.setSize(25, 25);  
    }  
    public MazeGrid(boolean mark, int width, int height) {//界面加载时全部设置为白色  
        this.mark = mark;  
        this.setSize(width, height); 
        this.setBackground(new Color(255, 255, 255));
        /*if (mark == true) {  
        	this.setBackground(new Color(255, 255, 255));  
        } else {  
            this.setBackground(new Color(30, 30, 30));  
        }*/
    }  
    public boolean isMark() {//是否通路  
        return mark;  
    }  
    public void setMark(boolean mark) {//标记通路  
        this.mark = mark;  
    }  
    public void paint(Graphics g) {//绘制迷宫，红色代表起点和终点，黑色代表墙，白色代表路  
        if (this.mark) {  
            if (this.isStart || this.isEnd) {//判断是否为起点和终点  
                this.setBackground(new Color(255,0,0));  
            } else  
                this.setBackground(new Color(255, 255, 255));  
        } else {  
            this.setBackground(new Color(30, 30, 30));  
        }  
        if (this.isPersonCome) {  
            g.setColor(Color.BLACK);  
            g.fillOval(2, 2, 15, 15);  
        }  
    }  
    public boolean isVisited() {//是否访问过  
        return isVisited;  
    }  
    public void setVisited(boolean isVisited) {//标记访问  
        this.isVisited = isVisited;  
    }  
    public boolean isPersonCome() {//是否走过  
        return isPersonCome;  
    }  
    public void setPersonCome(boolean isPersonCome) {//标记已走的路  
        this.isPersonCome = isPersonCome;  
    }  
    public boolean isStart() {//是否起点  
        return isStart;  
    }  
    public void setStart(boolean isStart) {//设置起点  
        this.isStart = isStart;  
    }  
    public boolean isEnd() {//是否终点  
        return isEnd;  
    }  
    public void setEnd(boolean isEnd) {//设置终点  
        this.isEnd = isEnd;  
    }  
}  