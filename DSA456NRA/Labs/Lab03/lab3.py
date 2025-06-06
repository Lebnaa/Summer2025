#
# Author:
# Student Number:
#
# Doubly Linked List Lab 3 - Completed Implementation
#

class DoublyLinked:

    class Node:
        def __init__(self, data, next=None, prev=None):
            self.data = data
            self.next = next
            self.prev = prev

        def get_data(self):
            return self.data

        def get_next(self):
            return self.next

        def get_previous(self):
            return self.prev

    def __init__(self, data=None):
        self.front = None
        self.back = None
        if data is not None:
            node = self.Node(data)
            self.front = self.back = node

    def get_front(self):
        return self.front

    def get_back(self):
        return self.back

    def push_front(self, data):
        new_node = self.Node(data)
        if not self.front:
            self.front = self.back = new_node
        else:
            new_node.next = self.front
            self.front.prev = new_node
            self.front = new_node

    def push_back(self, data):
        new_node = self.Node(data)
        if not self.back:
            self.front = self.back = new_node
        else:
            new_node.prev = self.back
            self.back.next = new_node
            self.back = new_node

    def pop_front(self):
        if not self.front:
            raise IndexError("pop_front() used on empty list")
        value = self.front.data
        self.front = self.front.next
        if self.front:
            self.front.prev = None
        else:
            self.back = None
        return value

    def pop_back(self):
        if not self.back:
            raise IndexError("pop_back() used on empty list")
        value = self.back.data
        self.back = self.back.prev
        if self.back:
            self.back.next = None
        else:
            self.front = None
        return value

class Sentinel:

    class Node:
        def __init__(self, data=None, next=None, prev=None):
            self.data = data
            self.next = next
            self.prev = prev

        def get_data(self):
            return self.data

        def get_next(self):
            return self.next

        def get_previous(self):
            return self.prev

    def __init__(self):
        self.head = self.Node()  # sentinel head
        self.tail = self.Node()  # sentinel tail
        self.head.next = self.tail
        self.tail.prev = self.head

    def get_front(self):
        return self.head.next if self.head.next != self.tail else None

    def get_back(self):
        return self.tail.prev if self.tail.prev != self.head else None

    def push_front(self, data):
        new_node = self.Node(data)
        first = self.head.next
        new_node.next = first
        new_node.prev = self.head
        self.head.next = new_node
        first.prev = new_node

    def push_back(self, data):
        new_node = self.Node(data)
        last = self.tail.prev
        new_node.prev = last
        new_node.next = self.tail
        last.next = new_node
        self.tail.prev = new_node

    def pop_front(self):
        if self.head.next == self.tail:
            raise IndexError("pop_front() used on empty list")
        node = self.head.next
        self.head.next = node.next
        node.next.prev = self.head
        return node.data

    def pop_back(self):
        if self.tail.prev == self.head:
            raise IndexError("pop_back() used on empty list")
        node = self.tail.prev
        self.tail.prev = node.prev
        node.prev.next = self.tail
        return node.data
