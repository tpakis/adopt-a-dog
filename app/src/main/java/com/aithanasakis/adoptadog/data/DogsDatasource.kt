package com.aithanasakis.adoptadog.data

import com.aithanasakis.adoptadog.models.Dog

interface DogsDatasource {
    suspend fun getDogs(): List<Dog>
}

class FakeDogsDatasource : DogsDatasource {

    override suspend fun getDogs(): List<Dog> {
        return dogsList
    }
}

private val dogsList = listOf(
    Dog(
        id = 1,
        name = "Rex",
        age = 3,
        description = "The German Shepherd is a breed of medium to large-sized working dog that originated in Germany. According to the FCI, the breed's English language name is German Shepherd Dog.",
        photoUrl = "https://upload.wikimedia.org/wikipedia/commons/d/d0/German_Shepherd_-_DSC_0346_%2810096362833%29.jpg",
        breed = "German Shepherd"
    ),
    Dog(
        id = 2,
        name = "Max",
        age = 7,
        description = "The Bulldog, also known as the English Bulldog or British Bulldog, is a medium-sized dog breed. It is a muscular, hefty dog with a wrinkled face and a distinctive pushed-in nose. The Kennel Club, the American Kennel Club, and the United Kennel Club oversee breeding records",
        photoUrl = "https://i.pinimg.com/originals/07/88/59/0788594bfd9f226adb366b203ee19dd2.jpg",
        breed = "Bulldog"
    ),
    Dog(
        id = 3,
        name = "Black",
        age = 4,
        description = "The Poodle is a dog breed that comes in three varieties: Standard Poodle, Miniature Poodle, and Toy Poodle. The breed’s origin is disputed: whether it descends from Germany as a type of water dog, or from the French Barbet.",
        photoUrl = "https://poodlereport.com/wp-content/uploads/2019/10/Canva-medium-size-poodle-1024x683.jpg",
        breed = "Poodle"
    ),
    Dog(
        id = 4,
        name = "Spoty",
        age = 1,
        description = "The Labrador Retriever, often abbreviated to Labrador, is a breed of retriever-gun dog from the United Kingdom that was developed from imported Canadian fishing dogs. The Labrador is one of the most popular dog breeds in a number of countries in the world, particularly in the Western world.",
        photoUrl = "https://upload.wikimedia.org/wikipedia/commons/thumb/3/34/Labrador_on_Quantock_%282175262184%29.jpg/1200px-Labrador_on_Quantock_%282175262184%29.jpg",
        breed = "Labrador Retriever"
    ),
    Dog(
        id = 5,
        name = "Franky",
        age = 6,
        description = "The Siberian Husky is a medium-sized working sled dog breed. The breed belongs to the Spitz genetic family. It is recognizable by its thickly furred double coat, erect triangular ears, and distinctive markings, and is smaller than the similar-looking Alaskan Malamute.",
        photoUrl = "https://static.wikia.nocookie.net/jackyshunde/images/c/ca/Siberian-husky.jpg/revision/latest?cb=20120926133906&path-prefix=de",
        breed = "Siberian Husky"
    ),
    Dog(
        id = 6,
        name = "Ira",
        age = 2,
        description = "The Australian Terrier is a small breed of dog of the terrier dog type. The breed was developed in Australia, although the ancestral types of dogs from which the breed descends were from Great Britain.",
        photoUrl = "https://doggiedesigner.com/wp-content/uploads/2018/03/australian-yorkshire-terrier-on-grass-scaled.jpg",
        breed = "Australian Terrier"
    ),
    Dog(
        id = 7,
        name = "Jack",
        age = 1,
        description = "The Chihuahua is one of the smallest breeds of dog, and is named after the Mexican state of Chihuahua",
        photoUrl = "https://www.thesprucepets.com/thmb/Kh-xt27-hqeQzhyr9288cl_P64I=/1396x1396/smart/filters:no_upscale()/twenty20_f84c633e-705e-4bf8-a724-00cdea750d8d-590b51893df78c92837b18d6.jpg",
        breed = "Chihuahua"
    ),
)