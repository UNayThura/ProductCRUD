
class CategoryListController{

    constructor(CategoryListServiece){
        this.CategoryListServiece = CategoryListServiece
        this.categoryName = ''
        this.categoryList = [];
        this.duplicateName = '';
        this.CategoryListServiece.getCategoryList().then((response) =>{
            for (let i = 0; i < response.length; i++) {
                response[i].editing = false;
            }

            this.categoryList = response
        })
    }

    edit(id){
        
        this.categoryList.map(category =>{
            if(category.editing == true) category.editing = false

            if(category.id == id){
                this.categoryName = category.name;
            }
        })
        
    }

    cancle(id){
        
        this.categoryList.map( category =>{
            if(category.id == id) category.editing = false
        })
        this.duplicateName = ''
    }

    save(id, index){
        console.log('id >> ', id, this.categoryName)
        this.categoryList.map( category => {
            if(category.id == id){
                if(this.categoryName == ''){
                    category.editing = true

                }else{
                    let obj = {
                        id: id,
                        name: this.categoryName
                    }
                    
                    this.CategoryListServiece.update(obj).then((response) => {
                        console.log('response ', response.name)
                        this.categoryList[index] = obj
                    }).catch(e=>{
                        this.duplicateName = e.status
                        category.editing = true
                    })
                    category.editing = false;
                }
            }
        })

        // if(this.categoryName == ''){
        //     this.categoryList.map( category => {
        //         if(category.id == id){
        //             category.editing = true;
        //         }
        //     })
        // }else{
        //     let obj = {
        //         id: id,
        //         name: this.categoryName
        //     }
            
        //     this.CategoryListServiece.update(obj).then(() => {
        //         this.categoryList[index] = obj
        //     })
        //     this.categoryList.map( category => {
        //         if(category.id == id){
        //             category.editing = false;
        //         }
        //     })
        // }

    }
    
}

CategoryListController.$inject = ['CategoryListServiece']
export default CategoryListController