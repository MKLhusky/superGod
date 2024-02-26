mod view;
mod func;

use view::login::*;
use func::native::*;
use leptos::*;



fn main() {
    mount_to_body(|| {
        view! {
            <App/>
        }
    })
}
