package next;

import java.util.Arrays;
import java.util.Random;


public class Generation {
  private int p[][];

  Generation() {
    p = new int[32][32];// 初始赋值0
    Random random = new Random();// 默认构造方法
    for (int i = 1; i < 31; i++)
      for (int j = 1; j < 31; j++) {
        int x = random.nextInt(2);
        p[i][j] = x;
      }
  }

  // 测试辅助方法
  public void clear() {
    for (int i = 0; i < 32; i++) {
      Arrays.fill(p[i], 0);
    }
  }

  public void set(int i, int j, int x) {
    p[i][j] = x;
  }
  // 获取i,j坐标的状态

  public int select(int i,int j) {
		return p[i][j];
	}

  // 测试函数，在控制台打印数组状态
  public void printTheLife() {
    for (int i = 0; i < 32; i++) {
      for (int j = 0; j < 32; j++) {

        System.out.print(p[i][j] + " ");
      }
      System.out.println();
    }
  }

  // 获取每个坐标的周围为生的细胞数量
  public int getNeighborCount(int m, int n) {
    int count = 0;
    for (int i = m - 1; i <= m + 1; i++)
      for (int j = n - 1; j <= n + 1; j++)
        count += select(i, j);
    count = count - select(m, n);
    return count;

  }

  // 一次迭代，更新每个细胞的生死状态
  public void update() {

    // 获取每个细胞周围的活细胞数目
    int[][] count = new int[31][31];
    for (int i = 1; i < 31; i++) {
      for (int j = 1; j < 31; j++) {
        count[i][j] = getNeighborCount(i, j);
      }
    }

    for (int i = 1; i < 31; i++) {
      for (int j = 1; j < 31; j++) {
        switch (count[i][j]) {
        case 3:
          p[i][j] = 1;
          break;
        case 2:
          break;
        default:
          p[i][j] = 0;
          break;
        }
      }
    }

  }

  /*
   * public void start() { Timer timer = new Timer(); timer.schedule(new
   * TimerTask() { public void run() { update(); printTheLife();
   * System.out.println(); } },0,500); }
   */
  public static void main(String[] args) {
    Generation g = new Generation();
    /*
     * g.printTheLife(); g.update(); System.out.println(); g.printTheLife();
     */
    g.clear();
    g.printTheLife();
  }
}
