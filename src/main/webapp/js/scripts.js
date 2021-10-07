function refreshData(){
    let userSelect = document.getElementById('user');
    let depId = userSelect.options[userSelect.selectedIndex].label;
    let departSelect = document.getElementById('department')
    let index;
    for (index = 0; index < departSelect.options.length; index++){
        if(departSelect.options[index].value === depId){
            break;
        }
    }
    departSelect.selectedIndex = index;
    let nameBox = document.getElementById('name');
    nameBox.value = userSelect.options[userSelect.selectedIndex].text;
}
