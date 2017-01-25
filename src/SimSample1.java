public class SimSample1 {

    static double totalTime = 10000000;  // 合計シミュレーション時間

    // コンストラクタ，初期値を生成する
    SimSample1 () {
    }

    // シミュレーションの実施
    void cal() {
        double trial = 0.0; // 各試行の値
        int sum = 0;        // 値の合計

        for (int i = 0; i < totalTime; i++) {
            // サイコロを振る
            trial = 6 * Math.random();

            // 合計値を更新する
            if (trial <= 1.0) sum = sum + 1;
            else if (trial <= 2.0) sum = sum + 2;
            else if (trial <= 3.0) sum = sum + 3;
            else if (trial <= 4.0) sum = sum + 4;
            else if (trial <= 5.0) sum = sum + 5;
            else sum = sum + 6;


        }

        // 結果の出力（サイコロの目の期待値）
        System.out.println("E = " + (sum / totalTime));
    }

    // main関数
    public static void main (String args[]) {
        SimSample1 qsim = new SimSample1();
        qsim.cal();
    }
}
