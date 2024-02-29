pub mod native {
    use wasm_bindgen::prelude::*;
    use serde::{Deserialize, Serialize};

    #[wasm_bindgen(module="/public/js/include.js")]
    extern "C" {

        #[wasm_bindgen(js_name = "getName")]
        pub fn get_name() ->  String;


        #[wasm_bindgen(js_name = "getToday")]
        pub fn get_today() ->  String;
    }

    #[wasm_bindgen]
    extern "C" {

        #[cfg(feature = "gui")]
        //调用后台函数  tauri 封装的rust函数 #[tauri::command]
        #[wasm_bindgen(js_namespace = ["window", "__TAURI__", "tauri"])]
        pub fn invoke(cmd: &str, args: JsValue) -> JsValue;

        #[cfg(feature = "gui")]
        #[wasm_bindgen(js_namespace = ["window", "__TAURI__", "os"])]
        pub  async fn arch() -> JsValue;

        //绑定的js的console.log
        #[wasm_bindgen(js_namespace = console)]
        pub fn log(s: &str);
    }

    #[derive(Serialize, Deserialize)]
    pub struct PrintText<'a>{
        pub text: &'a str
    }


    #[macro_export]
    macro_rules! console_log {
    ($($t:tt)*) => (
        let  x = &format_args!($($t)*).to_string();
        log(x);
        #[cfg(feature = "gui")] {
            use serde_wasm_bindgen::to_value;
            let  arg = to_value(&PrintText{text:x}).unwrap();
            invoke("print",arg);
        }
    )
    }
}