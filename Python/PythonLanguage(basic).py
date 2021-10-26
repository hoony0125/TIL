
# 한줄 주석문

'''
범위 주석문 1
'''

"""
범위 주석문 1
"""

"""
'''
code 1
'''
code 2
"""

'''
# String
a = "Hello Python"
print(a)
print(type(a))

# Number
a = 123
print(a)
print(type(a))

a = 12 * 24
print(a)

a, b = 9, 4
print(a + b)
print(a, b)

# Boolean
a = True
print(a)
print(type(a))

a = (10 == 10)
print(a)

b = (10 > 100)
print(b)
'''

'''
# if
a = 25

if a < 50:
    print('50보다 작다')

if a > 10 and a < 50:
    print('50보다 작다')

# if else
if a < 20:
    print('20보다 작다')
else:
    print('20보다 크다')

# else if
age = int(input("현재 나이를 입력하세요 = "))
print('age:', age)
if age < 10:
    print("유년층입니다")
elif age < 20:
    print("미성년입니다")
elif age < 30:
    print("성년입니다")
else:
    print("장령층입니다")
'''

'''
# for
for i in range(0, 5):
    print(i)

for i in range(0, 10, 2):
    print(i)

for i in [1, 3, 5, 7, 9]:
    print(i)

# 1부터 10까지의 합을 구하라.
sum = 0
for i in range(1, 11, 1):
    sum += i
    
print("sum:", sum)
print("sum: %d" %sum)
'''

'''
# while
str = "꿈은 이루어 진다"
i = 0
while i < 3:
    print(str)
    i = i + 1

print('-------------------')

# 입력을 받아서 입력한 숫자만큼 반복 출력하시오
i = int(input("loop count = "))
j = 1
flag = True
while flag:
    j = j + 1
    if i < j:
        flag = False
    print(str)
'''

'''
# break
w = 0
while True:   
    print("w = ", w)
    if w > 5:
        break    
    w += 1

# for문과 break문을 이용해서 1에서 20까지의 합이 100보다 가장 가깝고 작은 합을 구하시오
sum = 0
for i in range(1, 21, 1):
    sum += i
    if sum > 100:
        break;
sum -= i
print(sum)

# while문과 break문을 사용해서 입력한 숫자까지의 합을 구하시오
sum, w = 0, 0
j = int(input('number input = '))
while True:
    if w < j:
        w = w + 1
        sum += w
    elif w == j:
        break

print('1부터 %d까지의 합은 %d입니다' %(j, sum))
'''

# tuple : 변경할 수 없는 배열
str = "Python String"
print(str[0])
print(str[-1])
# str[0] = 'A'
# str[-1] = 'A'

# list
'''
a, b, c = 0, 0, 0
hap = 0
a = int(input("number 1 = "))
b = int(input("number 2 = "))
c = int(input("number 3 = "))
hap = a + b + c
print("hap:%d" %hap)
'''
mylist = [11, 22, 33]
hap = 0
'''
mylist[0] = int(input("number 1 = "))
mylist[1] = int(input("number 2 = "))
mylist[2] = int(input("number 3 = "))
'''

'''
hap = mylist[0] + mylist[1] + mylist[2]
print("hap:%d" %hap)
print(mylist)

# 추가
mylist.append(44)
mylist.append(55)
mylist.append(66)

print(mylist)
print(len(mylist))

print(mylist[-2])

mylist[-3] = 40
print(mylist)

print(mylist[0:3]) # 0 ~ (3-1)
print(mylist[2:5]) # 2 ~ (5-1)

print(mylist[2:])  # 2 ~  
print(mylist[:3])  # 0 ~ (3-1)

# 삭제
mylist.pop()
print(mylist)

# Sort
mylist.sort() # 오름
print(mylist)

mylist.sort(reverse=True) # 내림
print(mylist)

# 추가
mylist.insert(2, 111)
print(mylist)

# 삭제
mylist.remove(111)
print(mylist)



arr2 = [ [1, 2, 3, 4],
         [5, 6, 7, 8],
         [9, 10, 11, 12] ]
print(arr2[1][2])
'''


'''
# dictionary(map == json)
dict = { '번호':10, '이름':'장동건', '나이':24, '주소':'서울' }
print(dict)
print(type(dict))
print(dict['이름'])

# 수정 
dict['나이'] = 32
print(dict)

# 추가
dict['직업'] = '배우'
print(dict)

# key, value
print(dict.keys())
print(dict.values())

print('주소' in dict)
del dict['주소']
print(dict)

print('주소' in dict)
'''


# function
def myfunc1():
    print("myfunc1 call")
    
myfunc1()

def myfunc2(str='default value'):
    print('myfunc2 call')
    print('str : ', str)

myfunc2('string')
myfunc2()


def myfunc3():
    i = 20
    j = 30
    print(i + j)
    
myfunc3()

def myfunc4(n1, n2, cal):
    if cal == '+':
        print(n1 + n2)
    elif cal == '*':
        print(n1 * n2)
        
myfunc4(23, 34, '*')


# 외부 모듈(module) == library
# import Math.random
'''
import myModule

myModule.mydefine1()
myModule.mydefine2(100, 3)

import myModule as my

my.mydefine1()

import sys
print(sys.builtin_module_names) # 컴파일된 모든 모듈의 이름
'''


# class
class AutoMobile:
    name = ""
    velocity = 0
    
    def __init__(self, name):
        self.name = name
        print("AutoMobile __init__")
    
    def velocityPlus(self):  # self == this
        self.velocity = self.velocity + 1
        print("속도는 %d입니다" %self.velocity)
        
    def velocityDw(self):
        abc = 23        
        self.velocity = self.velocity - 1
        if self.velocity < 0:
            self.velocity = 0
        print("속도는 %d입니다" %self.velocity)
    
    def velocitySet(self, velocity):
        self.velocity = velocity
                

am = AutoMobile("sonata")
am.velocity = 20

am.velocitySet(10)
am.velocityPlus()
am.velocityDw()


















