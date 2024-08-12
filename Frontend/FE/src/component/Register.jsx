import axios from "axios";
import React, { useState } from "react";
import { Link } from "react-router-dom";

const Register = () => {
  const [submitting, setSubmitting] = useState(false);
  const [success, setSuccess] = useState(false);
  const [data, setData] = useState({
    email: "",
    firstName: "",
    lastName: "",
    password: "",
  });
  const onSubmit = async (e) => {
    e.preventDefault();
    setSubmitting(true);
    try {
      const response = await axios.post(
        "https://localhost:7190/api/User/SaveUsers",
        data
      );
       
      if (response.status === 200) {
        window.location.href = "/";
        setSuccess(true);
       
        // Xử lý thành công
      } else {
        // Xử lý lỗi
      }
    } catch (error) {
      console.log('====================================');
      console.log(error);
      console.log('====================================');
    } finally {
      setSubmitting(false);
    }
  };
  return (
    <>
      <section className="section-content padding-y">
        {/* ============================ COMPONENT REGISTER   ================================= */}
        <div className="card mx-auto" style={{ maxWidth: 520, marginTop: 40 }}>
          <article className="card-body">
            <header className="mb-4">
              <h4 className="card-title">Sign up</h4>
            </header>
            <form onSubmit={onSubmit}>
              <div className="form-row">
                <div className="col form-group">
                  <label>First name</label>
                  <input
                    type="text"
                    value={data.firstName}
                    onChange={(e)=>setData({...data,firstName:e.target.value})}
                    name="firstName"
                    className="form-control"
                    placeholder
                  />
                </div>{" "}
                {/* form-group end.// */}
                <div className="col form-group">
                  <label>Last name</label>
                  <input
                    type="text"
                    value={data.lastName}
                    onChange={(e)=>setData({...data,lastName:e.target.value})}
                    name="lastName"
                    className="form-control"
                    placeholder
                  />
                </div>{" "}
                {/* form-group end.// */}
              </div>{" "}
              {/* form-row end.// */}
              <div className="form-group">
                <label>Email</label>
                <input
                  type="email"
                  name="email"
                  value={data.email}
                  onChange={(e)=>setData({...data,email:e.target.value})}
                  className="form-control"
                  placeholder
                />
                <small className="form-text text-muted">
                  We'll never share your email with anyone else.
                </small>
              </div>{" "}
              {/* form-group end.// */}
              <div className="form-row">
                <div className="form-group col-md-6">
                  <label>Create password</label>
                  <input
                    className="form-control"
                    name="password"
                    value={data.password}
                    onChange={(e)=>setData({...data,password:e.target.value})}
                    type="password"
                  />
                </div>{" "}
                {/* form-group end.// */}
              </div>
              <div className="form-group">
                <button
                  type="submit"
                  className="btn btn-primary btn-block"
                //   disabled={submitting}
                >
                  {" "}
                  Register
                </button>
              </div>{" "}
            </form>
          </article>
          {/* card-body.// */}
        </div>{" "}
        {/* card .// */}
        <p className="text-center mt-4">
          Have an account? <Link to={`/`}>Log In</Link>
        </p>
        <br />
        <br />
        {/* ============================ COMPONENT REGISTER  END.// ================================= */}
      </section>
    </>
  );
};

export default Register;
