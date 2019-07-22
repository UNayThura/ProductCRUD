export default class HomeService {
    constructor($http, $q) {
        this._$http = $http;
        this._$q = $q;
    }

    getData() {
        let request = {
            method: 'GET',
            url: 'https://jsonplaceholder.typicode.com/todos'
        };

        return this._$http(request).then((res) => {
            let data = res.data;
            return data.filter( d => d.id <=10);
        });
    }
}

HomeService.$inject = ['$http', '$q'];