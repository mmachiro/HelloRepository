public class QueueSim1c {
    static double totalTime = 10000000;  // 合計シミュレーション時間
    static double w;	// 平均滞在時間
    static int    n;	//到着数
    double[] wait_exp = new double[n];  // 残り滞在時間（指数分布）
    double[] wait_uni = new double[n];  // 残り滞在時間（一様分布）
    double[] wait_fix = new double[n];  // 残り滞在時間（一定分布）
    int count_exp = 0, count_uni = 0 , count_fix = 0;	// 入場客数の合計
    // コンストラクタ，初期値を生成する
    QueueSim1c () {
    	for (int i = 0; i < n; i++){
    		wait_exp[i] = -w * Math.log(Math.random());
    		wait_uni[i] = w * 2 * Math.random();
    		wait_fix[i] = w;
    	}
    }
    // シミュレーションの実施
    void cal() {
        for (int i = 0; i < totalTime; i++) {
        	for (int j = 0 ; j < n; j++){
        		wait_exp[j] = wait_exp[j] - 1;
        		wait_uni[j] = wait_uni[j] - 1;
        		wait_fix[j] = wait_fix[j] - 1;
        		// 退出時間が来た項を新しい乱数で置き換え、入場客数を更新

        		if (wait_exp[j] <= 0){
        			wait_exp[j] = -w * Math.log(Math.random());
        			count_exp = count_exp + 1;
        		}
        		if (wait_uni[j] <= 0){
        			wait_uni[j] = w * 2 * Math.random();
        			count_uni = count_uni + 1;
        		}
        		if (wait_fix[j] <= 0){
        			wait_fix[j] = w;
        			count_fix = count_fix + 1;
        		}
        	}
        }
        // 結果の出力（平均到着間隔）
        System.out.print("入場間隔（指数分布） = " + (totalTime / count_exp));
        System.out.print(" 入場間隔（一様分布） = " + (totalTime / count_uni));
        System.out.println(" 入場間隔（一定分布） = " + (totalTime / count_fix));
    }

    // main関数
    public static void main (String args[]) {

    	w = Double.valueOf(args[0]);
    	n = Integer.valueOf(args[1]);
    	System.out.println("平均滞在時間 : " + w + " 行列人数: " + n);
		QueueSim1c qsim = new QueueSim1c();
		qsim.cal();


    }
}
