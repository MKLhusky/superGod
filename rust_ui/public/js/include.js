// import {arch} from '@tauri-apps/api/os'

export function getName(){
    // arch().then(value => console.log(value))
    return "张三";
}

export function getToday() {
    return moment(new Date()).format('YYYY-MM-DD');

}