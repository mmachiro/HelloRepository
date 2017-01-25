public class QueueSim3bk {

	// M/M/1 モデルのシミュレーションプログラム
	// 到着間隔はポアソン分布
	// 窓口使用時間は指数分布
	// 窓口数は1
    static double totalTime = 10000000;  // 合計シミュレーション時間
    static double w = 120;				 // 平均滞在時間
    static double t = 60;				 // 平均到着間隔
    double pt ;							// 平均「到着間隔（ポアソン）

    int count_kyaku_tennai;				//	平均系内客数
    int count_kyaku_machi;				//	平均待ち客数
    int total_count_kyaku_tennai;		//	合計店内客数
    int total_count_kyaku_machi;		//	合計待ち客数

    double[] wait_uni = new double[4];  // 残り滞在時間（一様分布）

    int count_exp = 0, count_uni = 0 , count_fix = 0;	// 入場客数の合計
    int count_pt = 0;

    // コンストラクタ，初期値を生成する
    QueueSim3bk () {

    	// ポアソン分布
    	pt = poisson(t);
    	//for(int i = 0 ; i < 4 ; i++){
    	//	wait_uni[i] = -w * Math.log(Math.random());
    	//}
    }

	static int poisson(double lambda) {
		double xp;
		int k = 0;
		xp = Math.random();
		while (xp >= Math.exp(-lambda)) {
		xp = xp * Math.random();
		k = k + 1;
		}
		return (k);
	}

    // シミュレーションの実施
    void cal() {

		count_kyaku_tennai =  0;
		count_kyaku_machi  =  0;
		int r = 0;

		for (int i = 0; i < totalTime; i++) {

    		pt	= pt - 1;

    		// 客が到着した
    		if (pt <= 0){


    			//窓口が塞がっていない
    			if(count_kyaku_tennai < 4){

    				count_kyaku_tennai++;

    				for (r = 0 ; r < 4; r++){
    	    			if(wait_uni[r] < 1){
        					wait_uni[r] = -w * Math.log(Math.random());
        					break;
    	    			}
    	    		}
    			}else{
    				// 窓口が塞がっている
    				count_kyaku_machi++;
    			}
    			pt = poisson(t);
    		}


        	// 窓口の処理時間を進める

    		for (r = 0 ; r < 4; r++){

    			// 4窓口の時間を進める
    			if ( wait_uni[r] > 0 ) {

    				wait_uni[r]--;

    				// 窓口が解放された
    				if(wait_uni[r] <= 0 ) {
    					if(count_kyaku_machi > 0 ){
    						count_kyaku_machi--;
    	    				wait_uni[r] = -w * Math.log(Math.random());
    					}
    					count_kyaku_tennai--;
    				}
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

    		QueueSim3bk qsim = new QueueSim3bk();
    		qsim.cal();

    }
}
