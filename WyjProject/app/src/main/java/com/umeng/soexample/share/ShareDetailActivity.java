package com.umeng.soexample.share;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMEmoji;
import com.umeng.socialize.media.UMImage;
import com.umeng.socialize.media.UMVideo;
import com.umeng.socialize.media.UMusic;
import com.umeng.socialize.utils.SocializeUtils;
import com.umeng.soexample.R;
import com.umeng.soexample.backLayout.BaseActivity;
import com.umeng.soexample.utils.Defaultcontent;
import com.umeng.soexample.utils.StyleUtil;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ShareDetailActivity extends BaseActivity implements AdapterView.OnItemClickListener {

    @BindView(R.id.list)
    ListView list;
    private SHARE_MEDIA share_media;
    private ArrayList<String> styles = new ArrayList<>();
    private UMImage umImageLocal, umImageUrl;
    private UMVideo video;
    private UMusic music;
    private UMEmoji emoji;
    private File file;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share_detail);
        ButterKnife.bind(this);
        initData();
    }

    private void initData() {
        share_media = (SHARE_MEDIA) getIntent().getSerializableExtra("type");
        initStyle(share_media);
        initMedia();
        list.setAdapter(new ShareTypeAdapter(this, styles));
        list.setOnItemClickListener(this);

    }

    private void initMedia() {
        umImageLocal = new UMImage(ShareDetailActivity.this, R.mipmap.fengjing);
        umImageUrl = new UMImage(this, Defaultcontent.imageurl);
        music = new UMusic(Defaultcontent.musicurl);
        music.setTitle("音乐标题");
        music.setThumb(new UMImage(this, R.mipmap.fengjing));
        music.setDescription("music description");
//        music.setTitle("QQ音乐分享标题");
        video = new UMVideo(Defaultcontent.videourl);
        video.setThumb(new UMImage(this, R.mipmap.fengjing));//视频封面
        video.setTitle("视频标题");
        video.setDescription("video description");//正文描述
        emoji = new UMEmoji(this, "http://img5.imgtn.bdimg.com/it/u=2749190246,3857616763&fm=21&gp=0.jpg");
        emoji.setThumb(umImageLocal);
        file = new File(this.getFilesDir() + "test.txt");
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (SocializeUtils.File2byte(file).length <= 0) {
            String content = "U-share分享";
            byte[] contentInBytes = content.getBytes();
            try {
                FileOutputStream fop = new FileOutputStream(file);
                fop.write(contentInBytes);
                fop.flush();
                fop.close();

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void initStyle(SHARE_MEDIA share_media) {
        switch (share_media) {
            case WEIXIN:
                styles.add(StyleUtil.TEXT);
                styles.add(StyleUtil.IMAGELOCAL);
                styles.add(StyleUtil.IMAGEURL);
                styles.add(StyleUtil.WEB11);
                styles.add(StyleUtil.MUSIC11);
                styles.add(StyleUtil.VIDEO11);
                styles.add(StyleUtil.EMOJI);
                break;
            case WEIXIN_CIRCLE:
                styles.add(StyleUtil.TEXT);
                styles.add(StyleUtil.IMAGELOCAL);
                styles.add(StyleUtil.IMAGEURL);
                styles.add(StyleUtil.WEB11);
                styles.add(StyleUtil.MUSIC11);
                styles.add(StyleUtil.VIDEO11);
                break;
            case WEIXIN_FAVORITE:
                styles.add(StyleUtil.TEXT);
                styles.add(StyleUtil.IMAGELOCAL);
                styles.add(StyleUtil.IMAGEURL);
                styles.add(StyleUtil.WEB11);
                styles.add(StyleUtil.MUSIC11);
                styles.add(StyleUtil.VIDEO11);
                break;
            case QQ:
                styles.add(StyleUtil.IMAGELOCAL);
                styles.add(StyleUtil.IMAGEURL);
                styles.add(StyleUtil.WEB11);
                styles.add(StyleUtil.MUSIC11);
                styles.add(StyleUtil.VIDEO11);
                break;
            case QZONE:
                styles.add(StyleUtil.TEXT);
                styles.add(StyleUtil.IMAGELOCAL);
                styles.add(StyleUtil.IMAGEURL);
                styles.add(StyleUtil.WEB11);
                styles.add(StyleUtil.MUSIC11);
                styles.add(StyleUtil.VIDEO11);
                break;
            case SINA:
                styles.add(StyleUtil.TEXT);
                styles.add(StyleUtil.TEXTANDIMAGE);
                styles.add(StyleUtil.IMAGELOCAL);
                styles.add(StyleUtil.IMAGEURL);
                styles.add(StyleUtil.WEB11);
                styles.add(StyleUtil.MUSIC11);
                styles.add(StyleUtil.VIDEO11);
                break;
        }
    }

    private UMShareListener shareListener = new UMShareListener() {
        @Override
        public void onResult(SHARE_MEDIA platform) {
            Toast.makeText(ShareDetailActivity.this, "成功了", Toast.LENGTH_LONG).show();
        }

        @Override
        public void onError(SHARE_MEDIA platform, Throwable t) {
            Toast.makeText(ShareDetailActivity.this, "失败" + t.getMessage(), Toast.LENGTH_LONG).show();
        }

        @Override
        public void onCancel(SHARE_MEDIA platform) {
            Toast.makeText(ShareDetailActivity.this, "取消了", Toast.LENGTH_LONG).show();

        }
    };

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        if (styles.get(i).toString().equals(StyleUtil.TEXT)) {//纯文本
            new ShareAction(ShareDetailActivity.this).withText(Defaultcontent.text)
                    .setPlatform(share_media)
                    .setCallback(shareListener).share();
        } else if (styles.get(i).toString().equals(StyleUtil.IMAGELOCAL)) {//纯图片本地
            new ShareAction(ShareDetailActivity.this).withMedia(umImageLocal)
                    .setPlatform(share_media)
                    .setCallback(shareListener).share();
        } else if (styles.get(i).toString().equals(StyleUtil.IMAGEURL)) {//纯图片http
            new ShareAction(ShareDetailActivity.this).withMedia(umImageUrl)
                    .setPlatform(share_media)
                    .setCallback(shareListener).share();

        } else if (styles.get(i).toString().equals(StyleUtil.WEB11)//链接（有标题，有内容）
                || styles.get(i).equals(StyleUtil.WEB00)//链接（无标题，无内容）
                || styles.get(i).equals(StyleUtil.WEB10)//链接（有标题，无内容）
                || styles.get(i).equals(StyleUtil.WEB01)) {//链接（无标题，有内容）
            new ShareAction(ShareDetailActivity.this)
                    .withText(Defaultcontent.text)
                    .withMedia(umImageLocal)
                    .withTargetUrl(Defaultcontent.url)
                    .withTitle(Defaultcontent.title)
                    .setPlatform(share_media)
                    .setCallback(shareListener).share();
        } else if (styles.get(i).equals(StyleUtil.MUSIC11)//音乐（有标题，有内容）
                || styles.get(i).equals(StyleUtil.MUSIC00)//音乐（无标题，无内容）
                || styles.get(i).equals(StyleUtil.MUSIC10)//音乐（有标题，无内容）
                || styles.get(i).equals(StyleUtil.MUSIC01)) {//音乐（无标题，有内容）
            new ShareAction(ShareDetailActivity.this).withMedia(music).withTargetUrl(Defaultcontent.url)
                    .setPlatform(share_media)
                    .setCallback(shareListener).share();
        } else if (styles.get(i).equals(StyleUtil.VIDEO11)//视频（有标题，有内容）
                || styles.get(i).equals(StyleUtil.VIDEO00)
                || styles.get(i).equals(StyleUtil.VIDEO01)
                || styles.get(i).equals(StyleUtil.VIDEO10)) {
            new ShareAction(ShareDetailActivity.this).withMedia(video).withTargetUrl(Defaultcontent.url)
                    .setPlatform(share_media)
                    .setCallback(shareListener).share();
        } else if (styles.get(i).equals(StyleUtil.EMOJI)) {//微信表情（有标题，有内容）
            new ShareAction(ShareDetailActivity.this)
                    .withMedia(emoji)
                    .setPlatform(share_media)
                    .setCallback(shareListener).share();
        } else if (styles.get(i).equals(StyleUtil.FILE)) {//文件
            new ShareAction(ShareDetailActivity.this)
                    .withFile(file)
                    .withText(Defaultcontent.text)
                    .withTitle(Defaultcontent.title)
                    .setPlatform(share_media)
                    .setCallback(shareListener).share();
        }
    }
}
