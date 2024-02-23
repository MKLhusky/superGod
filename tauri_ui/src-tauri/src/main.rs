// Prevents additional console window on Windows in release, DO NOT REMOVE!!
#![cfg_attr(not(debug_assertions), windows_subsystem = "windows")]


#[tauri::command]
fn print(text: &str){
    println!("{}",text);
}

fn main() {
    tauri::Builder::default()
        .invoke_handler(tauri::generate_handler![print])
        .run(tauri::generate_context!())
        .expect("error while running tauri application");
}
