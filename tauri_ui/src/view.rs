pub mod login {

    use crate::func::native::*;
    use leptos::*;
    use crate::console_log;

    #[component]
    pub fn App() -> impl IntoView {
        view! {
        <main>
        <button
            on:click = move |_|  {
            console_log!("aaaaa");
        }
        >
        "Click me"
        </button>
        </main>
    }
    }
}