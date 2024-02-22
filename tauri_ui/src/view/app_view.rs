
use leptos::*;
use crate::func::*;


#[component]
pub fn App() -> impl IntoView {

    let (count, set_count) =create_signal(0);

    view! {
        <main>
        <button
            on:click = move |_| {
            // set_count.set(3);
            set_count.update(|e| *e += 2);
            // alert("调用到了");
        }
        >
        "Click me:"
        {move || count.get()}
        </button>
        </main>
    }
}