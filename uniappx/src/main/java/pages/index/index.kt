@file:Suppress("UNCHECKED_CAST", "USELESS_CAST", "INAPPLICABLE_JVM_NAME")
package uni.UNIA3163E1;
import io.dcloud.uniapp.*;
import io.dcloud.uniapp.extapi.*;
import io.dcloud.uniapp.framework.*;
import io.dcloud.uniapp.runtime.*;
import io.dcloud.uniapp.vue.*;
import io.dcloud.uniapp.vue.shared.*;
import io.dcloud.uts.*;
import io.dcloud.uts.Map;
import io.dcloud.uts.Set;
import io.dcloud.uts.UTSAndroid;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Deferred;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.async;
import uts.sdk.modules.veSdk.gotoDemoActivity;
import uts.sdk.modules.veSdk.init;
import uts.sdk.modules.veSdk.play;
import uts.sdk.modules.veSdk.camera;
import io.dcloud.uniapp.extapi.showToast as uni_showToast;
open class GenPagesIndexIndex : BasePage {
    constructor(instance: ComponentInternalInstance) : super(instance) {
        onLoad(fun(_: OnLoadOptions) {}, instance);
    }
    @Suppress("UNUSED_PARAMETER", "UNUSED_VARIABLE")
    override fun `$render`(): Any? {
        val _ctx = this;
        val _cache = this.`$`.renderCache;
        return createElementVNode("view", null, utsArrayOf(
            createElementVNode("image", utsMapOf("class" to "logo", "src" to "/static/logo.png")),
            createElementVNode("button", utsMapOf("onClick" to _ctx.buttonClick), toDisplayString(_ctx.title2), 9, utsArrayOf(
                "onClick"
            )),
            createElementVNode("button", utsMapOf("onClick" to _ctx.initSdk), toDisplayString(_ctx.title3), 9, utsArrayOf(
                "onClick"
            )),
            createElementVNode("button", utsMapOf("onClick" to _ctx.testPlay), toDisplayString(_ctx.title5), 9, utsArrayOf(
                "onClick"
            )),
            createElementVNode("button", utsMapOf("onClick" to _ctx.cameraClick), toDisplayString(_ctx.title4), 9, utsArrayOf(
                "onClick"
            ))
        ));
    }
    open var title2: String by `$data`;
    open var title3: String by `$data`;
    open var title4: String by `$data`;
    open var title5: String by `$data`;
    @Suppress("USELESS_CAST")
    override fun data(): Map<String, Any?> {
        return utsMapOf("title2" to "uts跳转原生Activity", "title3" to "初始化sdk", "title4" to "相机录制", "title5" to "测试VECore播放");
    }
    override fun `$initMethods`() {
        this.buttonClick = fun() {
            console.log("按钮被点了》》》", " at pages/index/index.uvue:18");
            this.title2 = "clicked";
            var ret = gotoDemoActivity(fun(msg) {
                console.log("result：" + msg, " at pages/index/index.uvue:21");
                this.title2 = msg;
            }
            );
            this.title2 = "clicked2" + ret;
            console.log("按钮被点了》》》" + ret, " at pages/index/index.uvue:27");
            if (!ret) {
                uni_showToast(ShowToastOptions(icon = "none", title = "需要在自定义基座中运行"));
            }
        }
        ;
        this.initSdk = fun() {
            var ret = init();
            this.title3 = "初始化：" + ret;
        }
        ;
        this.cameraClick = fun() {
            var ret = camera();
            this.title4 = "录制:" + ret;
        }
        ;
        this.testPlay = fun() {
            var ret = play(fun(msg) {
                console.log("result：" + msg, " at pages/index/index.uvue:45");
                this.title5 = "VE:" + msg;
            }
            );
            this.title5 = "clicked2" + ret;
            console.log("VECore》》》" + ret, " at pages/index/index.uvue:50");
            if (!ret) {
                uni_showToast(ShowToastOptions(icon = "none", title = "需要在自定义基座中运行"));
            }
        }
        ;
    }
    open lateinit var buttonClick: () -> Unit;
    open lateinit var initSdk: () -> Unit;
    open lateinit var cameraClick: () -> Unit;
    open lateinit var testPlay: () -> Unit;
    companion object {
        val styles: Map<String, Map<String, Map<String, Any>>>
            get() {
                return normalizeCssStyles(utsArrayOf(
                    styles0
                ), utsArrayOf(
                    GenApp.styles
                ));
            }
        val styles0: Map<String, Map<String, Map<String, Any>>>
            get() {
                return utsMapOf("logo" to padStyleMapOf(utsMapOf("height" to 100, "width" to 100, "marginTop" to 100, "marginRight" to "auto", "marginBottom" to 25, "marginLeft" to "auto")), "title" to padStyleMapOf(utsMapOf("fontSize" to 18, "color" to "#8f8f94", "textAlign" to "center")));
            }
        var inheritAttrs = true;
        var inject: Map<String, Map<String, Any?>> = utsMapOf();
        var emits: Map<String, Any?> = utsMapOf();
        var props = normalizePropsOptions(utsMapOf());
        var propsNeedCastKeys: UTSArray<String> = utsArrayOf();
        var components: Map<String, CreateVueComponent> = utsMapOf();
    }
}
