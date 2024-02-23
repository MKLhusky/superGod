use wasm_bindgen::prelude::*;
use serde::{Deserialize, Serialize};
#[wasm_bindgen]
extern "C" {
    //调用后台函数  tauri 封装的rust函数 #[tauri::command]
    #[wasm_bindgen(js_namespace = ["window", "__TAURI__", "tauri"])]
    pub fn invoke(cmd: &str, args: JsValue) -> JsValue;

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
        use serde_wasm_bindgen::to_value;

        let  x = &format_args!($($t)*).to_string();
        log(x);
        let  arg = to_value(&PrintText{text:x}).unwrap();
        invoke("print",arg).as_string().unwrap();
    )
}


