function sort(arr, i, j) {
    if (i >= j) {
        return;
    }
    let p = i, q = j;
    let temp = arr[p];
    while (p < q) {
        while (p < q && arr[q] >= temp) {
            q-=1;
        }
        arr[p] = arr[q];
        while (p < q && arr[p] <= temp) {
            p+=1;
        }
        arr[q] = arr[p];
    }
    arr[q] = temp;
    sort(arr, i, q - 1);
    sort(arr, q + 1, j);
}

let arr = [234, 57, 12, 123, 346, 1234, 2];

sort(arr, 0, arr.length - 1);