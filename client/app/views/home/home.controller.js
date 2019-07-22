import { isBuffer } from "util";
import { forEach } from "@uirouter/core";

class HomeController {


  'ngInject';
  constructor(HomeService,$scope) {
    this.name = 'home';
    this.taskString = ""
    this.task = '';
    this.saveText = '';
    this.taskList = [];
    this.idList = [];
    HomeService.getData().then(function (response) {
     
      this.taskList = response
      for (let i = 0; i < this.taskList.length; i++) {
        this.taskList[i].editing = false;
        this.idList.push(this.taskList[i].id);
      }
      
    }.bind(this))

  }

  add(){
    if(this.task == ''){
      console.log('Invalid Input');
    }else {
      const max = this.idList.reduce((a, b) => Math.max(a, b))
      const id = max+1;
      this.idList.push(id);
      let obj = { id: id, title: this.task, completed: false, editing: false}
      this.taskList.push(obj);;
    }
    this.task = '';
  }

  toggleComplete(toggleCompleteId){
    this.taskList.map(task=>{
      if(task.id==toggleCompleteId) task.completed = !task.completed;
    })
  }

  delete(deleteId){
   this.taskList= this.taskList.filter( element => element.id !== deleteId);
  }

  edit(editId){

    for (let i = 0; i < this.taskList.length; i++) {

      if(this.taskList[i].editing == true){
        this.taskList[i].editing = false;
      }
    }

    for (let i = 0; i < this.taskList.length; i++) {
      if(this.taskList[i].id == editId){
        this.taskList[i].editing = true;
        this.saveText = this.taskList[i].title;
        console.log('edit >>> ', this.taskList[i].editing, this.taskList[i].id);
      }
    }
  }

  save(saveId){
    console.log('save id >>> ', saveId);
    for (let i = 0; i < this.taskList.length; i++) {
      if(this.taskList[i].id == saveId && this.saveText !== ''){
        this.taskList[i].title = this.saveText;
        console.log('save text >> ', this.saveText);
      }
    }
    if(this.saveText == ''){
      this.saveText = this.taskList[saveId].title;
    }
  }

  cancel(cancelId){
    for (let i = 0; i < this.taskList.length; i++) {
      if(this.taskList[i].id == cancelId){
        this.saveText = this.taskList[i].title;
      }
    }
  }
}

HomeController.$inject = ['HomeService','$scope'];

export default HomeController;
