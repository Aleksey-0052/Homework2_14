package org.example.service;

import org.example.exceptions.ElementNotFoundException;
import org.example.exceptions.InvalidIndexException;
import org.example.exceptions.NullItemException;
import org.example.exceptions.StorageIsFullException;

import java.util.Arrays;

public class IntegerListImpl implements IntegerList {

    private final Integer[] storage;

    private int size;

    public IntegerListImpl() {

        storage = new Integer[10];
    }

    public IntegerListImpl(int initSize) {

        storage = new Integer[initSize];
    }

    public Integer[] getStorage() {

        return storage;
    }

    @Override
    public Integer add(Integer item) {
        validateSize();
        validateItem(item);

        storage[size++] = item;
        return item;
    }

    @Override
    public Integer add(int index, Integer item) {
        validateSize();
        validateItem(item);
        validateIndex(index);

        if (index == size) {
            storage[size++] = item;
            return item;
        }
        System.arraycopy(storage, index, storage, index + 1, size - index);
        storage[index] = item;
        size++;
        return item;

    }

    @Override
    public Integer set(int index, Integer item) {
        validateItem(item);
        validateIndex(index);

        storage[index] = item;
        return item;
    }

    @Override
    public Integer remove(Integer item) {
        validateItem(item);

        int index = indexOf(item);
        if (index == -1) {
            throw new ElementNotFoundException("Такой элемент не найден");
        }

        for (int i = 0; i < size; i++) {
            if (storage[i].equals(item)) {
                for (int j = i; j < size - 1; j++) {                     // Сдвигаем элементы влево
                    storage[j] = storage[j + 1];
                }
                storage[size - 1] = null;
                size--;
            }
        }
        return item;
    }
//    @Override
//    public Integer remove2(Integer item) {
//        validateItem(item);
//
//        int index = indexOf(item);
//
//        return remove(index);
//    }

    @Override
    public Integer remove(int index) {
        validateIndex(index);

        Integer item = storage[index];
        for (int j = index; j < size - 1; j++) {
            storage[j] = storage[j + 1];
        }
        storage[size - 1] = null;
        size--;
        return item;
    }

    @Override
    public boolean contains(Integer item) {
        Integer[] storageCopy = toArray();        // объявим новый массив и вызовем метод toArray, который создает копию имеющегося массива без пустых ячеек
        sortInsertion(storageCopy);               // вызовем приватный метод по сортировке массива "вставкой"
        return binarySearch(storageCopy, item);   // в качестве результата вызовем приватный метод по бинарному поиску элемента

    }

    @Override
    public int indexOf(Integer item) {
        for (int i = 0; i < storage.length; i++) {
            if (storage[i].equals(item)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Integer item) {
        for (int i = storage.length - 1; i >= 0; i--) {
            if (storage[i].equals(item)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public Integer get(int index) {
        validateIndex(index);
        return storage[index];
    }

    @Override
    public boolean equals(IntegerList otherList) {
//        if (otherList == null) {
//            return false;
//        }
//        if (size != otherList.size()) {
//            return false;
//        }
//        Integer[] str = this.toArray();
//        Integer[] storage2 = otherList.toArray();
//        for (int i = 0; i < str.length; i++) {
//            if (!str[i].equals(storage2[i])) {
//                return false;
//            }
//        }
//        return true;
        return Arrays.equals(this.toArray(), otherList.toArray());
    }

    @Override
    public int size() {

        return size;
    }

    @Override
    public boolean isEmpty() {

        return size == 0;
    }

    @Override
    public void clear() {
        for (int i = 0; i < storage.length; i++) {
            storage[i] = null;
        }
        size = 0;
    }

    @Override
    public Integer[] toArray() {

        return Arrays.copyOf(storage, size);
    }

    private void validateItem(Integer item) {
        if (item == null) {
            throw new NullItemException("На вход передан null");
        }
    }

    private void validateSize() {
        if (storage.length == size) {
            throw new StorageIsFullException("Хранилище товаров заполнено");
        }
    }

    private void validateIndex(int index) {
        if (index < 0 || index >= storage.length) {

            throw new InvalidIndexException("Ячейка с таким индексом отсутствует");
        }
    }

    private void sortInsertion(Integer[] arr) {    // создаем приватный метод по сортировке массива "вставкой"
        for (int i = 1; i < arr.length; i++) {
            Integer temp = arr[i];
            int j = i;
            while (j > 0 && arr[j - 1] >= temp) {
                arr[j] = arr[j - 1];
                j--;
            }
            arr[j] = temp;
        }
    }
    private boolean binarySearch(Integer[] arr, Integer item) {   // создаем приватный метод по бинарному поиску элемента
        int min = 0;
        int max = arr.length - 1;

        while (min <= max) {
            int mid = (min + max) / 2;

            if (item.equals(arr[mid])) {
                return true;
            }

            if (item < arr[mid]) {
                max = mid - 1;
            } else {
                min = mid + 1;
            }
        }
        return false;
    }

}
