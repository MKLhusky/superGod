pub mod login {
    // use serde::{Deserialize, Serialize};
    // use serde_wasm_bindgen::to_value;
    use sycamore::futures::spawn_local_scoped;
    use sycamore::prelude::*;
    // use wasm_bindgen::prelude::*;
    use crate::func::native::*;
    use crate::console_log;

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
    // };

    #[component]
    pub fn App<G: Html>(cx: Scope) -> View<G> {

        view! { cx,
            ul {
                p{
                    li ( class = "li"){"项目1"}
                    li ( class = "li2c"){"项目2"}
                    li ( class = "li 2ab"){ "项目" em {"3"}}
                    li {"项目4"}
                }
                p{
                    li ( class = "li"){"项目5"}
                    li ( class = "li2c"){"项目6"}
                    li ( class = "li 2ab"){ "项目" em {"7"}}
                    li {"项目8"}
                }

            }
        }
    }
}