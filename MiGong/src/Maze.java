import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.TimerTask; 
import javax.swing.*;
@SuppressWarnings("serial") 
public class Maze extends JFrame implements ActionListener {
    private int ROW = 25;// ROW ��COLĿǰ�ݶ�ֻ��������
    private int COL = 25;
    private JPanel panel;  
    private JPanel northPanel;  
    private JPanel centerPanel;  
    private MazeGrid grid[][];  
    private JButton restart;  
    private JButton dostart;  
    private List<String> willVisit = new ArrayList<String>(); // ��¼�˵�ǰ�����ƶ�����λ��
                                                              // ,��willvisit�в�����comed�еļ�¼��λ��
    private List<String> visited = new ArrayList<String>();
    private LinkedList<String> comed = new LinkedList<String>(); // �Ѿ��߹���λ��
    private long startTime;
    private long endTime;

    public Maze() {
        init();
        this.setTitle("���ݷ�--���Թ�");
        this.add(panel);
        this.pack(); // ����pack��ֻʣ��������
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // �û��������ڵĹرհ�ťʱ����ִ�еĲ���
    }

    public void init() {
        panel = new JPanel();  
        northPanel = new JPanel();  
        centerPanel = new JPanel();  
        panel.setLayout(new BorderLayout());  
        restart = new JButton("���������Թ�");  
        dostart = new JButton("��ʼ���Թ�");  
        grid = new MazeGrid[ROW][COL];  

        centerPanel.setLayout(new GridLayout(ROW, COL, 1, 1));
        centerPanel.setBackground(new Color(0, 0, 0));
        northPanel.add(restart);
        northPanel.add(dostart);

        dostart.addActionListener(this);
        restart.addActionListener(this);
        for (int i = 0; i < grid.length; i++)
            for (int j = 0; j < grid[i].length; j++) {
                if (j % 2 == 0 && i % 2 == 0)
                    grid[i][j] = new MazeGrid(true, 20, 20);
                else
                    grid[i][j] = new MazeGrid(false, 20, 20);
            }
        grid[0][0].setVisited(true);
        grid[0][0].setPersonCome(true);
        grid[0][0].setStart(true);
        visited.add("0#0");
        grid[ROW - 1][COL - 1].setEnd(true);
        grid = createMap(grid, 0, 0);
        for (int i = 0; i < grid.length; i++)
            for (int j = 0; j < grid[i].length; j++) {
                grid[i][j].repaint();
                centerPanel.add(grid[i][j]);
            }

        panel.add(northPanel, BorderLayout.NORTH);
        panel.add(centerPanel, BorderLayout.CENTER);
    }
    public MazeGrid[][] createMap(MazeGrid mazeGrid[][], int x, int y) {
        int visitX = 0;
        int visitY = 0;
        if (x - 2 >= 0) {
            if (!mazeGrid[x - 2][y].isVisited()) {
                willVisit.add((x - 2) + "#" + y);
            }
        }
        if (x + 2 < COL) {
            if (!mazeGrid[x + 2][y].isVisited()) {
                willVisit.add((x + 2) + "#" + y);
            }
        }
        if (y - 2 >= 0) {
            if (!mazeGrid[x][y - 2].isVisited()) {
                willVisit.add(x + "#" + (y - 2));
            }
        }
        if (y + 2 < ROW) {
            if (!mazeGrid[x][y + 2].isVisited()) {
                willVisit.add(x + "#" + (y + 2));
            }
        }
        if (!willVisit.isEmpty()) {
            int visit = (int) (Math.random() * willVisit.size());
            String id = willVisit.get(visit);
            visitX = Integer.parseInt(id.split("#")[0]);
            visitY = Integer.parseInt(id.split("#")[1]);
            mazeGrid[(visitX + x) / 2][(visitY + y) / 2].setMark(true);

            mazeGrid[visitX][visitY].setVisited(true);
            if (!visited.contains(id)) {// �������ӵ��ѷ�����ȥ
                visited.add(id);
            }
            willVisit.clear();
            createMap(mazeGrid, visitX, visitY);
        } else {
            if (!visited.isEmpty()) {
                String id = visited.remove(visited.size() - 1);// ȡ�����һ��Ԫ��
                visitX = Integer.parseInt(id.split("#")[0]);
                visitY = Integer.parseInt(id.split("#")[1]);
                mazeGrid[visitX][visitY].setVisited(true);
                createMap(mazeGrid, visitX, visitY);
            }
        }
        return mazeGrid;
    }

    public String goMaze(MazeGrid mazeGrid[][], int x, int y) {
        int comeX = 0;
        int comeY = 0;
        // left
        if (x - 1 >= 0) {
            if (mazeGrid[x - 1][y].isMark()) {
                if (!comed.contains((x - 1) + "#" + y))
                    willVisit.add((x - 1) + "#" + y);
            }
        }
        // right
        if (x + 1 < COL) {
            if (mazeGrid[x + 1][y].isMark()) {
                if (!comed.contains((x + 1) + "#" + y))
                    willVisit.add((x + 1) + "#" + y);
            }
        }
        // up
        if (y - 1 >= 0) {
            if (mazeGrid[x][y - 1].isMark()) {
                if (!comed.contains(x + "#" + (y - 1)))
                    willVisit.add(x + "#" + (y - 1));
            }
        }
        // down
        if (y + 1 < ROW) {
            if (mazeGrid[x][y + 1].isMark()) {
                if (!comed.contains(x + "#" + (y + 1)))
                    willVisit.add(x + "#" + (y + 1));
            }
        }
        if (!willVisit.isEmpty()) {
            int visit = (int) (Math.random() * willVisit.size());
            String id = willVisit.get(visit);
            comeX = Integer.parseInt(id.split("#")[0]);
            comeY = Integer.parseInt(id.split("#")[1]);
            mazeGrid[x][y].setPersonCome(false);
            mazeGrid[comeX][comeY].setPersonCome(true);
            mazeGrid[x][y].repaint();
            mazeGrid[comeX][comeY].repaint();
            willVisit.clear();
            comed.add(x + "#" + y);
        } else {
            if (!comed.isEmpty()) {
                String id = comed.removeLast();
                comeX = Integer.parseInt(id.split("#")[0]);
                comeY = Integer.parseInt(id.split("#")[1]);
                mazeGrid[x][y].setPersonCome(false);
                mazeGrid[comeX][comeY].setPersonCome(true);
                mazeGrid[x][y].repaint();
                mazeGrid[comeX][comeY].repaint();
                comed.addFirst(x + "#" + y);
            }
        }
        return comeX + "#" + comeY;
    }

    int comeX = 0;
    int comeY = 0;

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("���������Թ�")) {
            refreshMap(grid);
        } else if (e.getActionCommand().equals("��ʼ���Թ�")) {
            startTime = System.currentTimeMillis();
            dostart.setVisible(false);
            restart.setText("�Ѿ���ʼ�߳��Թ��������ظ�ˢ���Թ�");
            int delay = 1000;
            int period = 500;// ѭ�����
            java.util.Timer timer = new java.util.Timer();
            timer.scheduleAtFixedRate(new TimerTask() {
                public void run() {
                    if (grid[ROW - 1][COL - 1].isPersonCome()) {
                        endTime = System.currentTimeMillis();
                        JOptionPane.showMessageDialog(null, "�Ѿ��߳��Թ�����ʱ" + (endTime - startTime) / 1000 + "��", "��Ϣ��ʾ",
                                JOptionPane.ERROR_MESSAGE);
                        this.cancel();
                        restart.setText("���������Թ�");
                    } else {


                        String id = goMaze(grid, comeX, comeY);
                        comeX = Integer.parseInt(id.split("#")[0]);
                        comeY = Integer.parseInt(id.split("#")[1]);
                    }
                }
            }, delay, period);
        }
    }

    public void refreshMap(MazeGrid mazeGrid[][]) {
        comeX = 0;
        comeY = 0;
        willVisit.clear();
        visited.clear();
        comed.clear();
        this.remove(panel);
        init();
        this.add(panel);
        this.pack();
        this.setVisible(true);
    }

    public static void main(String args[]) {
        long start = System.currentTimeMillis();
        new Maze();
        long end = System.currentTimeMillis();
        System.out.println("ʹ��ArrayList�����Թ���ʱ��" + (end - start) + "����");
    }
}