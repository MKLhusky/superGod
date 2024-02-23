mod view;
use leptos::*;
use view::*;
mod func;


fn main() {
    mount_to_body(|| {
        view! {
            <App/>
        }
    })
}
