app.controller("product-ctrl", function ($scope, $http) {
    alert("Quan ly hang hoa")
    $scope.items=[];
    $scope.cates=[];
    $scope.form=[];


    $scope.initialize=function (){
        // load products
        $http.get("/rest/products").then(resp =>{
            $scope.items = resp.data;
            $scope.items.forEach(item =>{
                item.createDate = new Date(item.createDate)
            })
            var start = this.page * this.size;
            return $scope.items.slice(start, start + this.size);
        });
        //load category
        $http.get("/rest/categories").then(resp =>{
            $scope.cates = resp.data;
        });
    }

    //khoi dau
    $scope.initialize();

    // xoa form
    $scope.reset=function (){
        $scope.form={
            createDate: new Date(),
            image:'cloud-upload.jpg',
            available: true,
        }

    }

    //hien thi form
    $scope.edit = function (item){
        $scope.form=angular.copy(item);
        $(".nav-tabs a:eq(0)").tab('show');
    }

    // them sp moi
    $scope.create=function (){
        var item = angular.copy($scope.form);
        console.log('Create product: ', item)
        $http.post(`/rest/products`,item).then(resp =>{
            resp.data.createDate= new Date(resp.data.createDate)
            $scope.items.push(resp.data);
            $scope.reset();
            alert("Them moi san pham thanh cong")
        }).catch(error =>{
            alert("Them moi san pham that bai")
            console.log(error)
        });

    }

    // cap nhat sp
    $scope.update=function (){
        var item = angular.copy($scope.form);
        $http.put(`/rest/products/${item.id}`,item).then(resp =>{
            var index = $scope.items.findIndex(p => p.id === item.id);
            $scope.items[index] = item;
            alert("Cap nhat thanh cong")
        }).catch(error =>{
            alert("Cap nhat that bai");
            console.log(error);
        })
    }

    //xoa sp
    $scope.delete=function (item){
        $http.delete(`/rest/products/${item.id}`).then(resp =>{
            var index = $scope.items.findIndex(p => p.id === item.id);
            $scope.items.splice(index,1);
            $scope.reset();
            alert("Xoa thanh cong");
        }).catch(error => resp =>{
            alert("Xoa that bai")
            console.log(error);
        });
    }

    //upload hinh
    $scope.imageChanged= function (files){
        var data = new FormData();
        data.append('file', files[0]);
        $http.post(`/rest/upload/images`,data,{
            transformRequest: angular.identity,
            headers: {'Content-Type': undefined}
        }).then(resp =>{
            $scope.form.image = resp.data.name;
            console.log(resp.data.name);
        }).catch(error =>{
            alert("Loi upload hinh");
            console.log("Error",error);
        })
    }

    //Phan Trang
    $scope.pager={
        page:0,
        size:10,
        get items(){
            var start = this.page * this.size;
            return $scope.items.slice(start, start + this.size);
        },
        get count(){
            return Math.ceil(1.0*$scope.items.length/this.size);
        },
        fist(){
            this.page=0
        },
        prev(){
            this.page--;
            if (this.page<0){
                this.last();
            }
        },
        next(){
            this.page++;
            if (this.page >= this.count){
                this.fist();
            }
        },
        last(){
            this.page=this.count-1
        }
    }

});