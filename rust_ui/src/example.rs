
use sycamore::prelude::*;

#[component]
pub fn Naruto<G: Html>(cx: Scope) -> View<G> {


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
            div (class = "start_ground center") {
                div (class = "start") {
                    header (class = "header") {
                        strong (class = "header-text") {
                            "漩涡 . 鸣人"
                        }
                        img (
                        class = "header-img",
                        src = "public/img/huoying/logo.jpg",
                        alt = "木叶logo"
                        ) {}
                    }
                    main ( class= "main") {
                            ul (class = "main-list") {
                                li{ "火之国·木叶隐村"}
                                li{ "第七班"}
                                li{ "下忍"}
                                li{ "火、风、雷、土、水、阳"}
                                li{ "人柱力、仙术、血继限界、通灵"}
                                li{ "第七代火影"}
                            }
                            img (
                            class = "main-img",
                            src = "public/img/huoying/mingren.jpg",
                            alt = "鸣人照片"
                            ) {}
                    }
                    footer (class = "footer") {
                        span (class = "footer-text") {
                            "我向来有话直说，说到做到，这就是我的忍道!"
                        }
                    }

                }
            }
        }

}