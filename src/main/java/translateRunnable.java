import com.tencentcloudapi.common.Credential;
import com.tencentcloudapi.common.exception.TencentCloudSDKException;
import com.tencentcloudapi.common.profile.ClientProfile;
import com.tencentcloudapi.tmt.v20180321.TmtClient;
import com.tencentcloudapi.tmt.v20180321.models.TextTranslateRequest;
import com.tencentcloudapi.tmt.v20180321.models.TextTranslateResponse;
public class translateRunnable implements Runnable {
    String s[]=null;
    translateRunnable(String s[]){
        this.s=s;
    }
    public void run() {
        for(String it:s){
            System.out.println(translate(it));
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    public static String translate(String sourceT){
        TextTranslateResponse textTranslateResponse=null;
        try{
            ClientProfile clientProfile = new ClientProfile();
            clientProfile.setSignMethod(ClientProfile.SIGN_TC3_256);
            //在此处填入腾讯云的api密钥
            Credential cred1 = new Credential("", "");
            TmtClient tmtClient= new TmtClient(cred1,"ap-guangzhou",clientProfile);
            TextTranslateRequest textTranslateRequest= new TextTranslateRequest();
            //填入腾讯api的id
            textTranslateRequest.setProjectId();
            textTranslateRequest.setSource("jp");
            textTranslateRequest.setTarget("zh");
            textTranslateRequest.setSourceText(sourceT);
            textTranslateResponse=tmtClient.TextTranslate(textTranslateRequest);
        } catch (TencentCloudSDKException e) {
            System.out.println(e.toString());
        }
        return textTranslateResponse.getTargetText();
    }
}
