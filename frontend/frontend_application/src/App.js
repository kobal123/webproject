import React, {useCallback} from 'react'
import {useDropzone} from 'react-dropzone'




function MyDropzone() {
  let files =[]
  const onDrop = useCallback(acceptedFiles => {
    files.push(acceptedFiles[0]);
  }, [])
  const {getRootProps, getInputProps, isDragActive} = useDropzone({onDrop})

  return (
    <div {...getRootProps()}>
      <input {...getInputProps()} />
      {
        isDragActive ?
          <p>Drop the files here ...</p> :
          <p>Drag 'n' drop some files here, or click to select files</p>
      }
    </div>
  )
}


export default MyDropzone;
