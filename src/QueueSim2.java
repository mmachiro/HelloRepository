public class QueueSim2 {

    static double totalTime = 10000000;  // 合計シミュレーション時間
    static double lambda = 5, mu = 3;  // 平均到着率，平均サービス時間

    // コンストラクタ，初期値を生成する
    QueueSim2 () {
    }


    // シミュレーションの実施
    void cal() {
        int queue = 0;  // 系内客数
        int l = 0;      // 系内客数の合計
        int lq = 0;     // 待ち客数の合計
        double arrival =-lambda * 60 * Math.log(Math.random());
        double service = mu * 60 * 2 * Math.random();

        for (int i = 0; i < totalTime; i++) {
            // 時計を1秒進める
            service = service - 1;
            arrival = arrival - 1;

            // 窓口に客がおらず，待ち行列に待っている人がいるとき
            if ((service <= 0) && (queue > 0)) {
                queue = queue - 1;
                service = mu * 60 * 2 * Math.random();
            }

            // 新たな客が到着したとき
            if (arrival <= 0) {
                queue = queue + 1;
                arrival =-lambda * 60 * Math.log(Math.random());

            }

            if (service > 0) l = l + 1;  // 窓口の客数を合計値に追加
            l = l + queue;    // 系内客数の合計値に待ち客数を追加
            lq = lq + queue;  // 待ち客数の合計値に待ち客数を追加
        }

        // 結果の出力（平均系内客数，平均待ち客数）
        System.out.println("L = " + (l / totalTime));
        System.out.println("Lq = " + (lq / totalTime));
    }

    // main関数
    public static void main (String args[]) {
    	QueueSim2 qsim = new QueueSim2();
        qsim.cal();
    }
}
