public class SimSample2 {

    static double totalTime = 10000000;  // 合計シミュレーション時間

    // コンストラクタ，初期値を生成する
    SimSample2 () {
    }

    // シミュレーションの実施
    void cal() {
        int in = 0;                   // 円内の点の数

        for (int i = 0; i < totalTime; i++) {
            // 点の位置を決定
            double x = Math.random(); // x座標の値
            double y = Math.random(); // y座標の値

            // 点の位置の評価（円内，円外）
            double v = Math.pow((x - 0.5), 2) + Math.pow((y - 0.5), 2);
            if (v <= 0.25)
              in++;
        }

        // 結果の出力（円の面積）
        System.out.println("S= " + (in / totalTime));
    }

    // main関数
    public static void main (String args[]) {
        SimSample2 qsim = new SimSample2();
        qsim.cal();
    }
}
