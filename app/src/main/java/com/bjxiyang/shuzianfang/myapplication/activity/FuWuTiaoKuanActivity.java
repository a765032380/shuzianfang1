package com.bjxiyang.shuzianfang.myapplication.activity;

import android.os.Bundle;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bjxiyang.shuzianfang.R;

/**
 * Created by Administrator on 2017/6/9 0009.
 * 服务条款页面
 */

public class FuWuTiaoKuanActivity extends MySwipeBackActivity {
    private LinearLayout lv_fanhui;
    private TextView tv_fuwutiaokuan_text;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fuwutiaokuan);

        tv_fuwutiaokuan_text= (TextView) findViewById(R.id.tv_fuwutiaokuan_text);
        String txt="<html>\n" +
                "<body>\n" +
                "   <p> 1、服务条款的确认和接纳本APP的各项内容和服务的所有\n" +
                "    权及解释权归希洋科技有限公司拥有。用户在接受本服务\n" +
                "    之前，请务必仔细阅读本条款。用户使用服务，\n" +
                "\t或通过完成注册程序，表示用户接受所有服务条款。</p>\n" +
                "　　\n" +
                "　　<p>2、用户同意：<br />\n" +
                "　　(1) 提供及时、详尽及准确的个人资料。<br />\n" +
                "　　(2) 不断更新注册资料、符合及时、详尽、准确的要求。<br />\n" +
                "　　如果用户提供的资料不准确，本公司有结束服务的权利。\n" +
                "　　本APP将不公开用户的姓名、地址、电子邮箱、帐号和电\n" +
                "    话号码等信息（请阅隐私保护条款）。\n" +
                "　　用户在本APP的任何行为必须遵循：<br />\n" +
                "　　(1) 传输资料时必须符合中国有关法规。<br />\n" +
                "　　(2) 使用信息服务不作非法用途和不道德行为。<br />\n" +
                "　　(3) 不干扰或混乱网络服务。<br />\n" +
                "　　(4) 遵守所有使用服务的网络协议、规定、程序和惯例。\n" +
                "\t用户的行为准则是以因特网法规，政策、程序和惯例为根据的。</p>\n" +
                "\n" +
                "　　<p>3、 用户的帐号、密码和安全性<br />\n" +
                "　　一旦成功注册成为会员，您将有一个密码和用户名。\n" +
                "　　用户将对用户名和密码的安全负全部责任。\n" +
                "\t另外，每个用户都要对以其用户名进行的所有活动和事件负全责。\n" +
                "\t您可以随时改变您的密码。\n" +
                "　　用户若发现任何非法使用用户帐号或存在安全漏洞的情况，请立即通告本公司。</p>\n" +
                "　　　　\n" +
                "　　<p>4、有限责任\n" +
                "　　本APP对任何由于使用服务引发的直接、间接、偶然及继起的损害不负责任。\n" +
                "　　这些损害可能来自（包括但不限于）：不正当使用服务，或传送的信息不符合规定等。</p>\n" +
                "　　\n" +
                "　　<p>5、对用户信息的存储和限制\n" +
                "　　本APP不对用户发布信息的删除或储存失败负责，\n" +
                "\t本公司有判定用户的行为是否符合服务条款的要求和精神的保留权利。\n" +
                "\t如果用户违背了服务条款的规定，有中断对其提供服务的权利。</p>\n" +
                "　　\n" +
                "　　<p>6、结束服务\n" +
                "　　本APP可随时根据实际情况中断一项或多项服务，不需对任何个人或第三方负责或知会。\n" +
                "　　同时用户若反对任何服务条款的建议或对后来的条款修改有异议，或对服务不满，用户可以行使如下权利：<br />\n" +
                "　　(1) 不再使用本公司的服务。<br />\n" +
                "　　(2) 通知本公司停止对该用户的服务。</p>\n" +
                "　　\n" +
                "　　<p>7、信息内容的所有权\n" +
                "　　本公司的信息内容包括：文字、软件、声音、相片、录象、图表；以及其它信息，\n" +
                "\t所有这些内容受版权、商标、标签和其它财产所有权法律的保护。\n" +
                "　　用户只能在授权下才能使用这些内容，而不能擅自复制、再造这些内容、或创造与内容有关的派生产品。</p>\n" +
                "　　\n" +
                "　　<p>8、隐私保护条款\n" +
                "　　本APP将严格保守用户的个人隐私，承诺未经过您的同意不将您的个人信息任意披露。\n" +
                "　　但在以下情形下，我们将无法再提供您前述保证而披露您的相关信息。这些情形包括但不限于：\n" +
                "　　* 为了您的交易顺利完成，我们不得不把您的某些信息，如您的姓名、联系电话、e-mail等提供给相关如物流服务方，\n" +
                "\t以便于他们及时与您取得联系，提供服务。\n" +
                "　　* 当您在本网站的行为违反的服务条款，或可能损害他人权益或导致他人遭受损害，\n" +
                "\t只要我们相信披露您的个人资料是为了辨识、联络或采取法律行动所必要的行动时。\n" +
                "　　* 法律法规所规定的必须披露或公开的个人信息。\n" +
                "　　* 当司法机关或其它有权机关依法执行公务，要求提供特定个人资料时，我们必须给予必要的配合。</p>\n" +
                "　　\n" +
                "　　<p>9、适用法律\n" +
                "　　上述条款将适用中华人民共和国的法律，所有的争端将诉诸于本公司所在地的人民法院。\n" +
                "　　如发生服务条款与中华人民共和国法律相抵触时，则这些条款将完全按法律规定重新解释，\n" +
                "\t而其它条款则依旧保持约束力。</p>\n" +
                "\t</body>\n" +
                "\t</html>";

        CharSequence charSequence= Html.fromHtml(txt);
//
        tv_fuwutiaokuan_text.setText(charSequence);
        //该语句在设置后必加，不然没有任何效果
        tv_fuwutiaokuan_text.setMovementMethod(LinkMovementMethod.getInstance());


        lv_fanhui= (LinearLayout) findViewById(R.id.lv_fanhui);
        lv_fanhui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
