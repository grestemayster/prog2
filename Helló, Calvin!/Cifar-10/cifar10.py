import keras
from keras.datasets import cifar10
from keras.layers import Dense, Activation, Flatten, Conv2D, MaxPooling2D
from keras.models import Sequential
from keras.utils import to_categorical,np_utils
from PIL import Image
from keras.optimizers import SGD
import numpy as np
import matplotlib.pyplot as plt
import tensorflow as tf
import os
(train_X,train_Y), (test_X,test_Y) = cifar10.load_data()

train_X = train_X.reshape(-1,32,32,3)
test_X = test_X.reshape(-1, 32,32, 3)

train_X = train_X.astype('float32')
test_X = test_X.astype('float32')
train_X = train_X / 255
test_X = test_X / 255

train_Y_one_hot = to_categorical(train_Y,10)
test_Y_one_hot = to_categorical(test_Y,10)

model = Sequential()

model.add(Conv2D(64, (3, 3), activation='relu', input_shape=(32, 32, 3)))
model.add(Conv2D(64, (3, 3), activation='relu'))
model.add(MaxPooling2D(pool_size=(2, 2)))

model.add(Flatten())
model.add(Dense(256))
model.add(Activation('relu'))

model.add(Dense(10))
model.add(Activation('softmax'))
sgd = SGD(lr=0.01, decay=1e-6, momentum=0.9, nesterov=True)
model.compile(loss=keras.losses.categorical_crossentropy, optimizer=sgd,metrics=['accuracy'])
img = Image.open('allat.png').convert("L")
img = np.resize(img,(32,32,3))
im2arr = np.array(img)
im2arr = im2arr.reshape(1,32,32,3)

model.fit(train_X, train_Y_one_hot, batch_size=64, epochs=1)

test_loss, test_acc = model.evaluate(test_X, test_Y_one_hot)
print('Test loss', test_loss)
print('Test accuracy', test_acc)

predictions = model.predict(test_X)
cifar_classes = ['airplane', 'automobile', 'bird', 'cat', 'deer', 'dog', 'frog', 'horse', 'ship', 'truck']
print(cifar_classes[np.argmax(np.round(predictions[0]))])
plt.imshow(test_X[0].reshape(32, 32,-1), cmap = plt.cm.binary)
plt.show()
print(cifar_classes[np.argmax(np.round(predictions[526]))])
plt.imshow(test_X[526].reshape(32, 32,-1), cmap = plt.cm.binary)
plt.show()

print(cifar_classes[np.argmax(np.round(model.predict(im2arr)))],np.argmax(np.round(model.predict(im2arr))))
plt.imshow(im2arr[0].reshape(32,32,3),cmap = plt.cm.binary)
plt.show()

