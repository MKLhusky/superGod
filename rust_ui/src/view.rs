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
            h1 {
                "我是一级标题"
            }
            p {
                "这是一个段落文本，在文本中有一个"
                span (class = "li2"){
                    "span element"
                }
                "并且还有一个"
                a ( href = "https://www.baidu.com", target = "_blank") {
                    "链接"
                }
            }

            p {
                "这是第二段。 包含了一个"
                em {
                    "强调"
                }
                "元素。"
            }
            ul {
                li {"项目1"}
                li ( class = "li2"){"项目2"}
                li { "项目" em {"3"}}
            }
        }
    }
}