package com.nyxwolves.wannabuy;

public class ReadMe {
    /**
     * @Chat
     * The chat directory contains the files involved in the chat module.
     * The ChatActivity.java contain a recyclerview which shows the incoming and outgoing chats.
     * The IncomingMessageViewHolder.java and OutgoingMessageViewHolder.java contain the view holder
     * to show the message received and sent respectively.
     * The Message.java contains the data holder of the message object.
     * The MessageAdapter.java is the recyclerview adapter for the activity.
     *
     * The chatactivity works on the firebase realtime database to get the chat data.
     * A tutorial for the same can be found at
     * https://in.udacity.com/course/firebase-in-a-weekend-by-google-android--ud0352
     *
     * @Notifications
     * The notification directory contains all files related to notifications
     * The notifications are powered by firebase. The firebase instance token is
     * generated in MyFirebaseMessagingService.java
     * The firebase token is uploaded to the server by a simple HTTPUrl method.
     *
     * @Contacts
     * The contact directory contains everything related to the contacts. When clicked on a contact,
     * the chat with that specific contact opens up.
     * The ContactActivity contains a recyclerview which shows the already contacted people.
     * The ContactViewHolder.java contains the view holder to show the contact.
     * The Contact.java contains the data holder of the contact object.
     * The ContactAdapter.java is the recyclerview adapter for the activity.
     * The ContactClickListener.java helps in item click listener for the contacts views.
     *
     * @Accounts
     * The Account activity is the profile page of the user logged in.
     * It also links the user to the settings and privacy policy and other options.
     *
     * @ImageUpload
     * The image upload is the activity which allows the user to upload the images to the server and
     * store there. The images can be multiple and stored with respect to the property.
     */
}