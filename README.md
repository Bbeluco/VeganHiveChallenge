# VeganHiveChallenge

The main goal in this challenge was to create a social media structure that allows users to register and login themselfs, create and interact with posts (liking and leaving some comments). It was recommended also the usage of NoSQL databases in this case, the company chose for Neo4j.

### Why NoSQL DB?
In the social media context, we have a bunch of related things, such as: who likes the post, its social network friends, the author of that post (that info we use to show to somebody else this content in their feed). This in large scale demands such a effective way to check as fast as possible those correlations, for this case NoSQL is better specially a database that is based on binary tree, in large scale its so much faster to retrieve information for the user based on what he/she in interested.

## Techs
- Java 17 (Spring boot, and Neo4J repository as ORM)
- Neo4j
- React
- HTML
- CSS
- Docker compose

All those technologies were requested or marked as good to have by the company, that's why I use then.

## Challenges during develop
To be honest I have never worked with Neo4j before, for me it was very fun to discover how it works and how to interact with this stack. I already had experience with NoSQL databases so some concepts were very clear to me.
The most difficult part for me was actually in the frontend, specially on keep reference over components children. I had to study a lot about how useRef works to understand how a map rendering a bunch of components could track a single field to me (In this paragraph, I'm referring to leave likes and know exactly what is the field that I had to update)

## Todos
- [] Unit test (specially in frontend part)
- [] Add loading effects
- [] Reset input field to write a post after create a new one

## Conclusion
This challenge teaches me a lot. I feel that I'm even more confient working with React and techs that I never see in my life before (like Neo4J).
If you see something that's considered wrong for you (like organization or even code that could be optimized), please, fell free to create a PR or send me a message on my LinkedIn.