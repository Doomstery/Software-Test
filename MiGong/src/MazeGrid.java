import java.awt.*;
@SuppressWarnings("serial")  
public class MazeGrid extends Canvas {  //Canvas�ؼ�����һ����������Ӧ�ó�����Ի��Ķ��������߿������û������Ľ������롣
                                    //�÷�������Frame
    private boolean mark;// ����Ƿ���ͨ·��TRUEΪͨ·��FALSEΪ��ͨ  
    private boolean isVisited;// ����Ƿ��Ƿ��ʹ���,�����������Թ���ʱ���жϵġ�  
    private boolean isPersonCome;// ����Ƿ��Ѿ��߹�  
    private boolean isStart;// �ж��Ƿ�Ϊ���  
    private boolean isEnd;// �ж��Ƿ�Ϊ����  
    public MazeGrid() {  
        this.setBackground(new Color(120, 0, 0));  
        this.setSize(25, 25);  
    }  
    public MazeGrid(boolean mark, int width, int height) {//�������ʱȫ������Ϊ��ɫ  
        this.mark = mark;  
        this.setSize(width, height); 
        this.setBackground(new Color(255, 255, 255));
        /*if (mark == true) {  
        	this.setBackground(new Color(255, 255, 255));  
        } else {  
            this.setBackground(new Color(30, 30, 30));  
        }*/
    }  
    public boolean isMark() {//�Ƿ�ͨ·  
        return mark;  
    }  
    public void setMark(boolean mark) {//���ͨ·  
        this.mark = mark;  
    }  
    public void paint(Graphics g) {//�����Թ�����ɫ���������յ㣬��ɫ����ǽ����ɫ����·  
        if (this.mark) {  
            if (this.isStart || this.isEnd) {//�ж��Ƿ�Ϊ�����յ�  
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
    public boolean isVisited() {//�Ƿ���ʹ�  
        return isVisited;  
    }  
    public void setVisited(boolean isVisited) {//��Ƿ���  
        this.isVisited = isVisited;  
    }  
    public boolean isPersonCome() {//�Ƿ��߹�  
        return isPersonCome;  
    }  
    public void setPersonCome(boolean isPersonCome) {//������ߵ�·  
        this.isPersonCome = isPersonCome;  
    }  
    public boolean isStart() {//�Ƿ����  
        return isStart;  
    }  
    public void setStart(boolean isStart) {//�������  
        this.isStart = isStart;  
    }  
    public boolean isEnd() {//�Ƿ��յ�  
        return isEnd;  
    }  
    public void setEnd(boolean isEnd) {//�����յ�  
        this.isEnd = isEnd;  
    }  
}  