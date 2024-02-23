
use leptos::*;
use crate::func::*;
use crate::console_log;


#[component]
pub fn App() -> impl IntoView {
    view! {
        <main>
        <button
            on:click = move |_|  {
            console_log!("你是: {}","张三");
        }
        >
        "Click me"
        </button>
        </main>
    }
}