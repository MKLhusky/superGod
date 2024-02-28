pub mod login {
    use serde::{Deserialize, Serialize};
    use serde_wasm_bindgen::to_value;
    use sycamore::futures::spawn_local_scoped;
    use sycamore::prelude::*;
    use sycamore::rt::Event;
    use wasm_bindgen::prelude::*;
    use crate::func::native::*;
    use crate::console_log;

    #[component]
    pub fn App<G: Html>(cx: Scope) -> View<G> {

        view! { cx,
        p{
            "你好牛逼"
        }
    }
    }
}