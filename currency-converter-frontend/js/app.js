let CurrencyConverterApp = angular.module('CurrencyConverterApp', []);

CurrencyConverterApp.controller('CurrencyController', function ($scope, $http) {

    $http.get("http://localhost:8080/api/v1/currency/all")
        .then(resp => {
                $scope.currencyList = resp.data;

                console.log($scope.currencyList);
            },
            resp => {
                console.error(resp);
            });

    $http.get("http://localhost:8080/api/v1/currency/all/history")
         .then(resp => {
                 $scope.currencyListHistory = resp.data;

                 console.log($scope.currencyListHistory);
            },
            resp => {
                console.error(resp);
             });


    var currentDateValue='';
    var currentTimeValue='';
    getDate();
    date_exchange = currentDateValue + "/\n" + currentTimeValue;
    status_exchange = 'исполнено';
    currency2_sum = '';

    $scope.currencyhistory = '';

    $scope.create = function () {

        if (currency2_sum!=='') {

            $http.post("http://localhost:8080/api/v1/currency/history", {
                'currency1Sum': currency1_sum,
                'currency2Sum': currency2_sum,
                'currency1Charcode': currency1_charcode,
                'currency2Charcode': currency2_charcode,
                'statusExchange': status_exchange,
                'dateExchange': date_exchange,
                'course': course

            })
                //    $http.post("http://localhost:8080/api/v1/currency", {'currency1_charcode:\'frontend\',currency2_charcode:\'null\',course:\'null\',currency1_sum:\'null\',currency2_sum:\'null\',status_exchange:\'null\',date_exchange':currency1_sum})
                .then(resp => {
                        $scope.currencyListHistory.push(resp.data);
                        console.log($scope.currencyListHistory);

                    },
                    resp => {
                        console.error(resp);
                    });


        }

        Initial();
    }

    $scope.delete = function (currencyhistory) {
        $http.delete("http://localhost:8080/api/v1/currency/history/" + currencyhistory.id)
            .then(resp => {
                    let ix = $scope.currencyListHistory.map(currencyhistory => currencyhistory.id).indexOf(currencyhistory.id);
                    $scope.currencyListHistory.splice(ix, 1);
                    console.log($scope.currencyListHistory);

                },
                resp => {
                    console.error(resp);
                });
    }

    $scope.select = function (currency) {
        $http.select("http://localhost:8080/api/v1/currency/" + currency.id)
            .then(resp => {
                    let ix = $scope.currencyList.map(currency => currency.id).indexOf(currency.id);
                   // $scope.currencyList.splice(ix, 1);
                    console.log(ix);

                },
                resp => {
                    console.error(resp);
                });
    }

    function getDate()
    {
        var date = new Date();
        var day = date.getDate();
        var month = date.getMonth()+1;
        var year = date.getFullYear();
        var hours = date.getHours();
        var minutes = date.getMinutes();
        var seconds = date.getSeconds();

        if(seconds < 10)
        {
            seconds = '0' + seconds;
        }
        if(minutes < 10)
        {
            minutes = '0' + minutes;
        }
        if(month < 10)
        {
            month = '0' + month;
        }

        currentDateValue = day + "." + month + "." + year;
        currentTimeValue = hours + ':' + minutes + ':' + seconds;
   }
    setInterval(getDate, 0);


})


function validate(evt) {

    var theEvent = evt || window.event;
    var key = theEvent.keyCode || theEvent.which;
    key = String.fromCharCode( key );
    var regex = /[0-9]|\./;
    if( !regex.test(key) ) {
        theEvent.returnValue = false;
        if(theEvent.preventDefault) {
            theEvent.preventDefault();
            var exchangeValue = theEvent.preventDefault() / 2;
            document.getElementById("exchangeValue").innerHTML = exchangeValue;
        }

    }
}



function getCourse(cur1,cur2) {
    // console.log(cur1);
    // console.log(cur2);

    Initial();


    if (cur1!=="") {
        val1 = JSON.parse(cur1);
        currency1_charcode = val1.charcode;
        document.getElementById("currency1_charcode").innerHTML = currency1_charcode + " равен ";
        document.getElementById("course_begin").innerHTML = 1;
    }
    if (cur2!=="") {
        val2 = JSON.parse(cur2);
        currency2_charcode = val2.charcode;
        document.getElementById("currency2_charcode").innerHTML = currency2_charcode;
        document.getElementById("currency22_charcode").innerHTML = currency2_charcode;
    }
    if (val1!==null && val2!==null) {
        course = (parseFloat(val1.value)/parseFloat(val2.value)).toFixed(4);
        console.log(course);
        document.getElementById("course").innerHTML = course;
     //   getSumExchange(currency1_sum);

    }

}


function getSumExchange(cur_sum1) {
    currency1_sum = cur_sum1;
    currency2_sum =  (cur_sum1 * this.course).toFixed(4);
    console.log(currency2_sum);
    document.getElementById("currency2_sum").innerHTML = currency2_sum;
}

function Initial() {

    currency1_sum='';
    currency2_sum='';
    document.getElementById('currency1_sum').value='';
    document.getElementById("currency2_sum").innerHTML = '';

}