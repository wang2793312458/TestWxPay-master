package net.sourceforge.simcpux.wxapi;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.tencent.mm.sdk.modelbase.BaseReq;
import com.tencent.mm.sdk.modelbase.BaseResp;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.sdk.openapi.WXAPIFactory;

import net.sourceforge.simcpux.R;
import net.sourceforge.simcpux.util.Constants;

public class WXPayEntryActivity extends Activity implements IWXAPIEventHandler{
	
	private static final String TAG = "MicroMsg.SDKSample.WXPayEntryActivity";
	
    private IWXAPI api;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pay_result);
        
    	api = WXAPIFactory.createWXAPI(this, Constants.APP_ID);
        api.handleIntent(getIntent(), this);
    }

	@Override
	protected void onNewIntent(Intent intent) {
		super.onNewIntent(intent);
		setIntent(intent);
        api.handleIntent(intent, this);
	}

	@Override
	public void onReq(BaseReq req) {
	}

	/**
	 * 支付通知
	 * @param resp
     */
	@Override
	public void onResp(BaseResp resp) {
		if(resp.errCode==0){
			Toast.makeText(this,"支付成功!",Toast.LENGTH_SHORT).show();

		}else if(resp.errCode==-1){
			Toast.makeText(this,"支付失败!",Toast.LENGTH_SHORT).show();

		}else if(resp.errCode==-2){
			Toast.makeText(this,"取消支付!",Toast.LENGTH_SHORT).show();

		}
		finish();
	}
}