public class QueueSim4 {


    static double totalTime = 10000000;  // 合計シミュレーション時間
    static double w = 180;				 // サービス時間
    static double t = 300;				 // 平均到着間隔
    double pt ;							// 平均「到着間隔（ポアソン）

    int count_kyaku_tennai;				//	平均系内客数
    int count_kyaku_machi;				//	平均待ち客数
    int total_count_kyaku_tennai;		//	合計店内客数
    int total_count_kyaku_machi;		//	合計待ち客数

    double wait_uni;  // 残り滞在時間（一様分布）

    int count_exp = 0, count_uni = 0 , count_fix = 0;	// 入場客数の合計
    int count_pt = 0;

    // コンストラクタ，初期値を生成する
    QueueSim4 () {

    	// ポアソン分布
    	pt = poisson(t);

    }

	static int poisson(double lambda) {
		double xp;
		int k = 0;
		xp = Math.random();
		while (xp >= Math.exp(-lambda)) {
		xp = xp * Math.random();
		k = k + 1;
		}
		System.out.println("ポアソン乱数" + k);
		return (k);
	}

    // シミュレーションの実施
    void cal() {

		count_kyaku_tennai =  0;
		count_kyaku_machi  =  0;

		for (int i = 0; i < totalTime; i++) {

    		pt	= pt - 1;

    		// 客が到着した
    		if (pt <= 0){


    			//窓口が塞がっていない
    			if(count_kyaku_tennai < 1){

    				count_kyaku_tennai++;

					if(wait_uni < 1){
    					wait_uni = -w * Math.log(Math.random());
   	    			}

    			}else{
    				// 窓口が塞がっている
    				count_kyaku_machi++;
    			}
    			pt = poisson(t);
    		}

			// 窓口の時間を進める
			if ( wait_uni > 0 ) {

				wait_uni--;

				// 窓口が解放された
				if(wait_uni <= 0 ) {
					if(count_kyaku_machi > 0 ){
						count_kyaku_machi--;
	    				wait_uni = -w * Math.log(Math.random());
					}
					count_kyaku_tennai--;
				}
			}


    		total_count_kyaku_tennai = total_count_kyaku_tennai + count_kyaku_tennai;
    		total_count_kyaku_machi = total_count_kyaku_machi + count_kyaku_machi;

        }


    	// 結果の出力（平均店内客数）(平均待ち客数)
        System.out.println("平均店内客数 = " + (total_count_kyaku_tennai / totalTime));
        System.out.println("平均待ち客数 = " + (total_count_kyaku_machi / totalTime));

    }

    // main関数
    public static void main (String args[]) {

    		QueueSim4 qsim = new QueueSim4();
    		qsim.cal();

    }
}
