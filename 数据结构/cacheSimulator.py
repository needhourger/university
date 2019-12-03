# coding: utf-8
from random import randint # randint model,used to populate the main memory with random data
"""
you are require to implement cache simulator in either Java or Python. Your program should do the following.
1.ask user the size of main menory,memory block,cache.
2.validate the user input.
3.Simulate the memory and cache of given size.
4.Populate the main memory with random data.
5.then ask user to choose one of the two operations.
    a)Read:if user selected this operation,you should ask user for the memory address(in hex)to read you data from ,check if the address is valid,check its hit or cache miss, and deliver the data to the user(simple print will sufficlent)
    b)write:if the suer selected this operation,your program should ask for the address and the new content. then perform cache lookup and update the cache and the memory.
6.You should provide the mechanism to check if the block is valid or not. Also, you need to provide the mechanism to keep a record of modified cache blocks.
"""

# Main memory class
class MainMemory(object):

    __cacheSize=0   # Cache size
    __blockSize=0   # Block size
    __groupNum=0    # Memory size/cache size
    __gblockNum=0   # The blocks number in a group: cache size/block size
    __memory=[]     # A list used to simulate memory

    # Constructor,receive the input size from user
    def __init__(self,memorySize=1024,cacheSize=256,blockSize=64):
        self.__cacheSize=cacheSize  # Set cache size
        self.__blockSize=blockSize  # Set block size
        self.__groupNum=memorySize//cacheSize   # Calculate group number
        self.__gblockNum=cacheSize//blockSize   # Calculater block number in a group
        # Populate the main memory with rand data
        for i in range(self.__groupNum):
            for j in range(self.__gblockNum):
                # the memory address:group ID,in group block ID,value
                unit={"groupId":i,"g-blockId":j,"value":randint(0,pow(2,blockSize))}
                self.__memory.append(unit)
    
    # Print Memory status
    def showMemory(self):
        i=1
        print("\nMain memory={")
        for x in self.__memory:
            if i%self.__gblockNum==0:
                print(hex(x.get("value")),"\t[Group {}]".format(x.get("groupId")))
            else:
                print(hex(x.get("value")),',',end='')
            i+=1
        print("}")

    # Get the value of memory by address(group id and block id in group)
    def get(self,groupId,gblockId):
        # Foreach memory to find the memmory block
        for x in self.__memory:
            if x.get("groupId")==groupId and x.get("g-blockId")==gblockId:
                return x.get("value")
        return None

    # update the address with new value
    def update(self,address=1,newValue=0):
        groupId=address//self.__cacheSize                               # Calculate the group ID
        gblockId=(address-groupId*self.__cacheSize)//self.__blockSize   # Calculate the block ID in group
        # Foreach the memory to find the target memory address
        for x in self.__memory:
            if x.get("groupId")==groupId and x.get("g-blockId")==gblockId:
                x["value"]=newValue
        

# Cache class
class Cache(object):
    __cacheSize=0   # Cache size
    __blockSize=0   # Block size
    __blockNum=0    # Block number
    __cache=[]      # a list used to simulate the cache

    # Constructor,receive the input size from user
    def __init__(self,cacheSize=256,blockSize=64):
        self.__cacheSize=cacheSize  # Set cache size
        self.__blockSize=blockSize  # Set block size
        self.__blockNum=cacheSize//blockSize    # Calculate the block number
        
        # init the cache 
        for i in range(self.__blockNum):
            # the cache address tag(record the memory's group id that this cache block stored),value
            unit={"tag":None,"value":None}
            self.__cache.append(unit)

    # Print cache status
    def showCache(self):
        print("\nCache={")
        # Foreach the cache and print the tag and value of every blocks
        for x in self.__cache:
            value=0 if x.get("value")==None else x.get("value")
            print("value: {} -- tag: {}".format(hex(value),x.get("tag")))
        print("}")

    # copy value (from memory) to the cache
    def __copyValue(self,id,tag,value):
        self.__cache[id]["tag"]=tag
        self.__cache[id]["value"]=value
    
    # Get the value at address
    def getAddress(self,address=1,memory=None):
        groupId=address//self.__cacheSize   # Calculate the group ID
        gblockId=(address-groupId*self.__cacheSize)//self.__blockSize   # Calculate the block ID in a group
        # Foreach the cache to find the target address. if finded, print 'hit' and return the value
        for x in self.__cache:
            if x.get("tag")==groupId:
                print("hit!")
                return hex(x.get("value"))
        # if not finded,copy the data from memory to the cache,return the value
        print("No hit...")
        self.__copyValue(gblockId,groupId,memory.get(groupId,gblockId))
        return hex(memory.get(groupId,gblockId))

    # Update the new value in memory at address and refresh the cache
    def update(self,address=1,newValue=0,memory=None):
        memory.update(address,newValue)     # Update the value in the memory of the receive address
        groupId=address//self.__cacheSize   # Calculate the group ID
        gblockId=(address-groupId*self.__cacheSize)//self.__blockSize   # Calculate the block ID in the group
        self.__copyValue(gblockId,groupId,memory.get(groupId,gblockId)) # Copy the new updated data from the memory to cache
                
# Check the input, valid integer
def intCheck(x):
    try:
        return int(x)
    except:
        print("Not a valid integer!")
        return None

# Check the input, hexadecimal
def hexCheck(x):
    try:
        return int(x,16)
    except:
        return None

# Main function
if __name__ == "__main__":
    memorySize=None
    # memorySize=1024
    # Input the memory size and check it
    while memorySize==None:
        memorySize=intCheck(input("Please input the size of memory(KB): "))
        print("")
    
    blockSize=None
    # blockSize=64
    # Input the block size and check it
    while blockSize==None:
        blockSize=intCheck(input("Please input the size of memory block(KB): "))
        if memorySize%blockSize!=0:
            print("Block size not valid!")
            blockSize=None
        print("")

    cacheSize=None
    # cacheSize=256
    # Input the cache size and check it
    while cacheSize==None:
        cacheSize=intCheck(input("Please input the size of cache(KB): "))
        if cacheSize%blockSize!=0 or memorySize%cacheSize!=0:
            print("Cache size not valid!")
            cacheSize=None
        print("")

    print("Simulator...\n\n")
    # Use the MainMemory class to simulate memory
    memory=MainMemory(memorySize,cacheSize,blockSize)
    # Use the Cache class to simulate cache
    cache=Cache(cacheSize,blockSize)
    
    while True:
        # Print the status of memory and cache
        memory.showMemory()
        cache.showCache()
        # Input the command,read or write
        choice=input("> Input your choice:[read/write]: ")
        if choice=="read":
            # Read data in memory
            # Input the hex address
            address=hexCheck(input("Please input the addres(in hex): "))
            # Check the address
            if address==None or address<0 or address>memorySize:
                print("Not a valid address")
                input()
                continue
            # Print the value of address
            print(cache.getAddress(address,memory))
            # input a enter, pause the program
            input()

        elif choice=="write":
            # Write data in a address
            # Input the hex address
            address=hexCheck(input("Please input address(in hex): "))
            # Check the address
            if address==None or address<=0 or address>memorySize:
                print("Not a valid address")
                input()
                continue
            # Input the new value
            newValue=hexCheck(input("Please input a new Value(in hex): "))
            # Check the value
            if newValue==None or newValue>pow(2,blockSize) or newValue<0:
                print("Not a valid value")
                input()
                continue
            # Update the address with new value
            cache.update(address,newValue,memory)
        else:
            continue





        