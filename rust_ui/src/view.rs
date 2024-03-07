pub mod login {

    // use serde::{Deserialize, Serialize};
    // use serde_wasm_bindgen::to_value;
    use sycamore::futures::spawn_local_scoped;
    use sycamore::prelude::*;
    // use wasm_bindgen::prelude::*;
    use crate::func::native::*;
    use crate::console_log;

    #[component]
    pub fn App<G: Html>(cx: Scope) -> View<G> {


    // let click= move |_| {
    //     console_log!("{}",get_name());
    //     console_log!("{}",get_today());
    //
    //     spawn_local_scoped(cx, async move {
    //     console_log!("{}","异步执行了");
    //     #[cfg(feature = "gui")]{
    //     let os_name=&(arch().await).as_string().unwrap();
    //     console_log!("{}",os_name);
    //     }
    //     })

        view! { cx,

        }
    }
}