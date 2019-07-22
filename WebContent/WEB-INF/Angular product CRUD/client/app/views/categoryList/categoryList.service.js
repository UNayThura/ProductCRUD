import { get } from "http";

export default class CategoryListServiece{

    constructor($http){
        this.$http = $http
    }

    getCategoryList(){
        let request = {
            method: 'GET',
            url: 'http://localhost:8080/SampleCRUD/category-list'
        }
        return this.$http(request).then((res) => {
            return res.data;
        })
    }

    update(data){
        let request = {
            method: 'POST',
            url: 'http://localhost:8080/SampleCRUD/updatecategory',
            data: JSON.stringify(data)
        }
        return this.$http(request).then(res => {
            console.log('res ', res)
            return res.data
        })
    }

}

CategoryListServiece.$inject = ['$http']