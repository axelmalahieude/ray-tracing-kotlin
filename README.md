# Kotlin Ray Tracing
Ray tracing application made in Kotlin, created out of interest in computer graphics and to learn about ray tracing

Goal is to be able to create a scene with various shapes on a surface, and render an image of the scene using ray tracing.

**Current progress**: the app is able to generate an image of a sphere by tracing the shadow ray to the light source 
and applying Lambertian reflectance. 
See examples in `images/`

![alt text](https://github.com/axelmalahieude/ray-tracing-kotlin/blob/master/images/2020-04-14%2021-18-56.png)

Features shown in this image: 2 spheres and a planar surface, with shadows cast from the light source being in the top left.

**Current to-dos**:
- Trace the reflected ray
- Implement Phong Shading
- Eventually try anti-aliasing

**Completed to-dos**:
- Created planar surface under spheres
- Added some linear transformations to help with placing the camera

