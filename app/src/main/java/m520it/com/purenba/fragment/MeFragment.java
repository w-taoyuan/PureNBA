package m520it.com.purenba.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import cn.sharesdk.framework.Platform;
import it.sephiroth.android.library.picasso.Picasso;
import m520it.com.purenba.R;
import m520it.com.purenba.activity.me.ExclusiveFreeFlowAcivity;
import m520it.com.purenba.activity.me.HotequipmentAcivity;
import m520it.com.purenba.activity.me.LoginActivity;
import m520it.com.purenba.activity.me.MemberCenterAcivity;
import m520it.com.purenba.activity.me.MyWalletAcivity;
import m520it.com.purenba.activity.me.SettingAcivity;
import m520it.com.purenba.activity.me.SystemMessageAcivity;
import m520it.com.purenba.activity.me.VoucherAcivity;
import m520it.com.purenba.base.BaseFragment;

/**
 * @author  Champion
 * @created on: 2017/4/26 14:41
 * @desc    我的界面
 */

public class MeFragment extends BaseFragment implements View.OnClickListener {

    private Intent mIntent;
    private ImageView mUserIcon;
    private TextView mUserName;
    private Platform mDf;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view =  View.inflate(getContext(), R.layout.fragement_me, null);
        //注册事件
        EventBus.getDefault().register(this);
        initView(view);
        return view;
    }

    private void initView(View view) {
        //设置点击跳转到登陆界面
        view.findViewById(R.id.login_ll).setOnClickListener(this);
        //用户头像
        mUserIcon = (ImageView) view.findViewById(R.id.user_icon);
        //用户名
        mUserName = (TextView) view.findViewById(R.id.user_name);

        //设置点击跳转到设置界面
        view.findViewById(R.id.setting_tv).setOnClickListener(this);
        //设置点击跳转到系统消息界面
        view.findViewById(R.id.system_message_iv).setOnClickListener(this);

        view.findViewById(R.id.diamonds_ll).setOnClickListener(this);//钻石
        view.findViewById(R.id.k_ll).setOnClickListener(this);//K
        view.findViewById(R.id.list_ll).setOnClickListener(this);//列表
        view.findViewById(R.id.rmb_ll).setOnClickListener(this);//人民币

        view.findViewById(R.id.vip_ll).setOnClickListener(this);//会员中心
        view.findViewById(R.id.games_ll).setOnClickListener(this);//游戏竞赛
        view.findViewById(R.id.hot_equipment_ll).setOnClickListener(this);//装备热卖
        view.findViewById(R.id.mall_points_ll).setOnClickListener(this);//积分商城

        view.findViewById(R.id.my_community_rl).setOnClickListener(this);//我的社区
        view.findViewById(R.id.my_attention_rl).setOnClickListener(this);//我的关注
        view.findViewById(R.id.my_guess_rl).setOnClickListener(this);//我的竞猜
        view.findViewById(R.id.system_message_rl).setOnClickListener(this);//系统消息
        view.findViewById(R.id.my_box_rl).setOnClickListener(this);//我的包厢
        view.findViewById(R.id.my_prize_rl).setOnClickListener(this);//我的奖品
        view.findViewById(R.id.wonderful_collection_rl).setOnClickListener(this);//精彩收藏
        view.findViewById(R.id.tencent_exclusive_free_flow_rl).setOnClickListener(this);//腾讯专属免流
    }

    /*登陆跳转*/
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            //点击后跳转到我的钱包界面
            case R.id.diamonds_ll :
            case R.id.k_ll :
            case R.id.list_ll :
                mIntent = new Intent(getActivity(), MyWalletAcivity.class);
                startActivity(mIntent);
                break;

            //点击后跳转到代金卷界面
            case R.id.rmb_ll:
                mIntent = new Intent(getActivity(), VoucherAcivity.class);
                startActivity(mIntent);
                break;

            //点击后跳转到QQ与微信登录界面
            case R.id.login_ll :                  //top登陆
            case R.id.games_ll :                  //游戏竞赛
            case R.id.mall_points_ll :            //积分商城
            case R.id.my_community_rl :           //我的社区
            case R.id.my_guess_rl :               //我的竞猜
            case R.id.my_box_rl :                 //我的包厢
            case R.id.my_prize_rl :               //我的奖品
            case R.id.wonderful_collection_rl :   //精彩收藏
//                if(mDf.getDb().getUserId()==null){
                    mIntent = new Intent(getActivity(), LoginActivity.class);
                    startActivity(mIntent);
//                }
                break;
            //点击后跳转到设置界面
            case R.id.setting_tv :         //
                mIntent = new Intent(getActivity(), SettingAcivity.class);
                startActivity(mIntent);
                break;
            //点击后跳转到系统消息界面
            case R.id.system_message_iv :  //
            case R.id.system_message_rl :  //系统消息
                mIntent = new Intent(getActivity(), SystemMessageAcivity.class);
                startActivity(mIntent);
                break;
            //点击后跳转到腾讯体育会员中心界面
            case R.id.vip_ll :             //腾讯体育会员中心
                mIntent = new Intent(getActivity(), MemberCenterAcivity.class);
                startActivity(mIntent);
                break;
            //点击后跳转到装备热卖界面
            case R.id.hot_equipment_ll:    //装备热卖
                mIntent = new Intent(getActivity(), HotequipmentAcivity.class);
                startActivity(mIntent);
                break;
            //点击后跳转到我的关注界面
            case R.id.my_attention_rl :    //我的关注
//                mIntent = new Intent(getActivity(), Acivity.class);
//                startActivity(mIntent);
                break;
            //点击后跳转到腾讯专属免流界面
            case R.id.tencent_exclusive_free_flow_rl :    //腾讯专属免流
                mIntent = new Intent(getActivity(), ExclusiveFreeFlowAcivity.class);
                startActivity(mIntent);
                break;
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onShowMessageEvent(Platform pf) {
        mDf = pf;
        if(mDf.getDb().getUserId()!=null){

            Picasso.with(getActivity()).load(pf.getDb().getUserIcon()).into(mUserIcon);
            mUserName.setText(pf.getDb().getUserName());
        }
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        //取消事件注册
        EventBus.getDefault().unregister(this);
    }
}
