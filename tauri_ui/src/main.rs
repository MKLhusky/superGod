mod view;
mod func;
use leptos::*;
use view::*;

fn main() {
    mount_to_body(|| {
        view! {
            <App/>
        }
    })
}
