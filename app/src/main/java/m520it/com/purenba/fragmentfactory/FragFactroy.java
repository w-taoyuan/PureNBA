package m520it.com.purenba.fragmentfactory;


import android.support.v4.app.Fragment;

import m520it.com.purenba.fragment.headfrag.CBAFragment;
import m520it.com.purenba.fragment.headfrag.DJFragment;
import m520it.com.purenba.fragment.headfrag.FJFragment;
import m520it.com.purenba.fragment.headfrag.GAMEFragment;
import m520it.com.purenba.fragment.headfrag.NCAAFragment;
import m520it.com.purenba.fragment.headfrag.NFLFragment;
import m520it.com.purenba.fragment.headfrag.NHLFragment;
import m520it.com.purenba.fragment.headfrag.NMQFragment;
import m520it.com.purenba.fragment.headfrag.OGFragment;
import m520it.com.purenba.fragment.headfrag.OZQFragment;
import m520it.com.purenba.fragment.headfrag.XJFragment;
import m520it.com.purenba.fragment.headfrag.XMFragment;
import m520it.com.purenba.fragment.headfrag.YGFragment;
import m520it.com.purenba.fragment.headfrag.YJFragment;
import m520it.com.purenba.fragment.headfrag.YZQFragment;
import m520it.com.purenba.fragment.headfrag.ZCFragment;
import m520it.com.purenba.fragment.headfrag.ZHFragment;
import m520it.com.purenba.fragment.headfrag.recommend.TJFragment;
import m520it.com.purenba.head.view.NBAFragment;
import m520it.com.purenba.head.view.YCFragment;

/**
 * Created by 亮 on 2017/4/25.
 */

public class FragFactroy {

    private static final int TJ_POSITION = 0;
    private static final int SP_POSITION = 1;
    private static final int SQ_POSITION = 2;

    public static Fragment createFragment(int position,String title) {

        if(position == TJ_POSITION) {
            return new TJFragment();
        }else {
            if ("NBA".equals(title)) {
                return new NBAFragment();
            } else if ("中超".equals(title)) {
                return new ZCFragment();
            } else if ("英超".equals(title)) {
                return new YCFragment();
            } else if ("西甲".equals(title)) {
                return new XJFragment();
            } else if ("欧冠".equals(title)) {
                return new OGFragment();
            } else if ("亚冠".equals(title)) {
                return new YGFragment();
            } else if ("意甲".equals(title)) {
                return new YJFragment();
            } else if ("德甲".equals(title)) {
                return new DJFragment();
            } else if ("法甲".equals(title)) {
                return new FJFragment();
            } else if ("NHL".equals(title)) {
                return new NHLFragment();
            } else if ("CBA".equals(title)) {
                return new CBAFragment();
            } else if ("NCAA".equals(title)) {
                return new NCAAFragment();
            } else if ("综合".equals(title)) {
                return new ZHFragment();
            } else if ("NFL".equals(title)) {
                return new NFLFragment();
            } else if ("电竞".equals(title)) {
                return new GAMEFragment();
            } else if ("世界杯南美区".equals(title)) {
                return new NMQFragment();
            } else if ("世预赛亚洲区".equals(title)) {
                return new YZQFragment();
            } else if ("世预赛欧洲区".equals(title)) {
                return new OZQFragment();
            } else if ("小梦战报".equals(title)) {
                return new XMFragment();
            }
        }
        return null;
    }
}
